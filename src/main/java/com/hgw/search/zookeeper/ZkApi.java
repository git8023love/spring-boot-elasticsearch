package com.hgw.search.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ZkApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ZooKeeper zkClient;

    /**
     * 判断指定节点是否存在
     * @param path 节点
     * @param needWatch 指定是否复用zookeeper中默认的Watcher
     * @return Stat
     */
    public Stat exists(String path, boolean needWatch) {
        try {
            return zkClient.exists(path, needWatch);
        } catch (KeeperException | InterruptedException e) {
            logger.error("【指定节点存在异常】{},{}", path, e);
            return null;
        }
    }

    /**
     * 指定节点是否存在，并设置监听
     *      三种监听类型： 创建，删除，更新
     * @param path 节点
     * @param watcher 传入指定的监听类
     * @return Stat
     */
    public Stat exists(String path, Watcher watcher) {
        try {
            return zkClient.exists(path, watcher);
        } catch (KeeperException | InterruptedException e) {
            logger.error("【指定节点存在异常】{},{}", path, e);
            return null;
        }
    }

    /**
     * 创建持久化节点
     * @param path 节点
     * @param data 数据
     * @param mode 节点类型
     *             <p>0: CreateMode.PERSISTENT 持久节点</p>
     *             <p>1: CreateMode.EPHEMERAL 临时节点</p>
     *             <p>2: CreateMode.PERSISTENT_SEQUENTIAL 持久节点顺序节点</p>
     *             <p>3: CreateMode.EPHEMERAL_SEQUENTIAL 临时节点顺序节点</p>
     * @return boolean
     */
    public boolean createNode(String path, String data, CreateMode mode) {
        try {
            zkClient.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (KeeperException | InterruptedException e) {
            logger.error("【创建持久化节点异常】{},{},{}", path, data, e);
            return false;
        }
    }

    public String createNodeStr(String path, String data, CreateMode mode) {
        try {
            return zkClient.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException | InterruptedException e) {
            logger.error("【创建持久化节点异常】{},{},{}", path, data, e);
            return null;
        }
    }

    /**
     * 修改持久化节点
     * @param path 节点
     * @param data 数据
     * @return boolean
     */
    public boolean updateNode(String path, String data) {
        try {
            zkClient.setData(path, data.getBytes(), -1);
            return true;
        } catch (KeeperException | InterruptedException e) {
            logger.error("【修改持久化节点异常】{},{},{}", path, data, e);
            return false;
        }
    }

    /**
     * 删除持久化节点
     * @param path 节点
     * @return boolean
     */
    public boolean delNode(String path) {
        try {
            zkClient.delete(path,  -1);
            return true;
        } catch (KeeperException | InterruptedException e) {
            logger.error("【删除持久化节点异常】{},{}", path,  e);
            return false;
        }
    }

    /**
     * 获取当前节点的子节点
     * @param path 节点
     * @param watch Boolean true 含子节点， false 不含子节点
     * @return List<String>
     */
    public List<String> getChildren(String path, boolean watch) {
        try {
            return zkClient.getChildren(path, watch);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取节点值
     * @param path 节点
     * @param watcher 传入指定的监听类
     * @return String
     */
    public String getData(String path, Watcher watcher) {
        Stat stat = new Stat();
        try {
            byte[] bytes = zkClient.getData(path, watcher, stat);
            return  new String(bytes);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }



    @PostConstruct
    public  void init(){
        String path="/zk-watcher-2";
        logger.info("【执行初始化测试方法。。。。。。。。。。。。】");
        createNode(path,"测试", CreateMode.EPHEMERAL);
        String value=getData(path,new WatcherApi());
        logger.info("【执行初始化测试方法getData返回值。。。。。。。。。。。。】={}",value);

        // 删除节点出发 监听事件
        delNode(path);
    }

}
