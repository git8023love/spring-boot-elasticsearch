package com.hgw.search.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hgw.search.dao.AppInfoDao;
import com.hgw.search.entity.AppInfo;
import com.hgw.search.entity.IndexDomain;
import com.hgw.search.service.AppInfoService;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppInfoServiceImpl implements AppInfoService {

    private static Logger log = LoggerFactory.getLogger(AppInfoServiceImpl.class);

    @Autowired
    TransportClient client;
    @Autowired
    AppInfoDao appInfoDao;

    private static String index = "elasticsearch";
    private static String type = "app_info";

    @Override
    public List<AppInfo> getAppInfo() {
        // 创建索引
        if (!indexExists(index)) {
            client.admin().indices().prepareCreate(index).get();
        }
        // 建立映射
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                    .startObject("id")
                    .field("type", "keyword")
                    .field("store", "yes")
                    .endObject()
                    .startObject("appName")
                    .field("type", "keyword")
                    .field("store", "yes")
                    .endObject()
                    .startObject("keywords")
                    .field("type", "keyword")
                    .field("store", "yes")
                    .endObject()
                    .startObject("createTime")
                    .field("type", "date")
                    .field("format", "yyyy-MM-dd HH:mm:ss")
                    .field("store", "yes")
                    .endObject()
                    .endObject()
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("mapping : " + mapping.getClass());
        PutMappingRequest mappingRequest = Requests.putMappingRequest(index).source(mapping).type(type);
        client.admin().indices().putMapping(mappingRequest).actionGet();

        // 插入数据
        List<AppInfo> appInfos = appInfoDao.findAll();
        for (AppInfo appInfo : appInfos) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("id", appInfo.getId());
            hashMap.put("appName", appInfo.getAppName());
            hashMap.put("keywords", appInfo.getKeywords());
            hashMap.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(appInfo.getCreateTime()));
            client.prepareIndex(index, type).setSource(hashMap).get();
            log.info("hashMap = {}", hashMap.toString());
        }
        return null;
    }

    @Override
    public Object findAppInfo(AppInfo appInfo) {
        QueryBuilder qb1 = QueryBuilders.termQuery("appName", appInfo.getAppName());
        // 模糊查询， “*”表示0到多个字符，而使用“？”表示一个字符
        QueryBuilder qb2 = QueryBuilders.wildcardQuery("appName", "*"+appInfo.getAppName()+"*");
        QueryBuilder qb3 = QueryBuilders.wildcardQuery("appName", appInfo.getAppName()+"?");
        QueryBuilder qb4 = QueryBuilders.wildcardQuery("appName", "?"+appInfo.getAppName()+"?");
        QueryBuilder qb5 = QueryBuilders.wildcardQuery("appName", appInfo.getAppName()+"?");
        QueryBuilder qb6 = QueryBuilders.wildcardQuery("appName", appInfo.getAppName()+"*");
        QueryBuilder qb7 = QueryBuilders.wildcardQuery("appName", "*"+appInfo.getAppName()+"*");

        HighlightBuilder highlightBuilder = new HighlightBuilder().field("appName").preTags("<span>").postTags("</span>");
    // .highlighter(highlightBuilder)
        SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.boolQuery().must(qb2)).get();

        SearchHits hits = response.getHits();
        if (hits.totalHits() > 0) {
            Arrays.stream(hits.getHits()).forEach(hit -> {
                System.out.println(hit);
//                String hight = "";
//                Text[] texts = hit.getHighlightFields().get("appName").getFragments();
//                for (Text str : texts) {
//                    hight += str;
//                    System.out.println(str.toString());
//                }
            });
            return hits.getHits();
        } else {
            System.out.println("搜到0条结果");
            //获取索引库字段
            List<String> fieldList = new ArrayList<String>();
            ClusterState cs = client.admin().cluster().prepareState().setIndices(index).execute().actionGet().getState();
            IndexMetaData imd = cs.getMetaData().index(index);
            MappingMetaData mdd = imd.mapping(type);
            Map<String, Object> mapProperties = new HashMap<String, Object>();

            try {
                mapProperties = mdd.getSourceAsMap();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fieldList = getIndexFieldList("", mapProperties);

            System.out.println("Field List:");
            for (String field : fieldList) {
                System.out.println(field);
            }
        }
        return null;
    }

    public List<String> getIndexFieldList(String fieldName, Map<String, Object> mapProperties) {
        List<String> fieldList = new ArrayList<String>();
        Map<String, Object> map = (Map<String, Object>) mapProperties.get("properties");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (((Map<String, Object>) map.get(key)).containsKey("type")) {
                fieldList.add(fieldName + "" + key);
            } else {
                List<String> tempList = getIndexFieldList(fieldName + "" + key
                        + ".", (Map<String, Object>) map.get(key));
                fieldList.addAll(tempList);
            }
        }
        return fieldList;
    }


    /**
     * 判断集群中{Index}是否存在
     *
     * @param index
     * @return 存在（true）、不存在（false）
     */
    public boolean indexExists(String index) {
        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = client.admin().indices().exists(request).actionGet();
        if (response.isExists()) {
            return true;
        }
        return false;
    }
}
