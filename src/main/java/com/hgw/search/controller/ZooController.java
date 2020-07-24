package com.hgw.search.controller;

import com.hgw.search.utils.Result;
import com.hgw.search.zookeeper.WatcherApi;
import com.hgw.search.zookeeper.ZkApi;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZooController {

    @Autowired
    ZkApi zkApi;


    @GetMapping("/create")
    public Result create(String path, String data) {
        Stat stat = zkApi.exists(path, false);
        if (stat == null) {
            zkApi.createNode(path, data, CreateMode.EPHEMERAL);
        }
        String _data = zkApi.getData(path, new WatcherApi());
        return new Result(_data);
    }

    @GetMapping("/update")
    public Result update(String path, String data) {
        Stat stat = zkApi.exists(path, false);
        String _data = "";
        if (stat != null) {
            zkApi.updateNode(path, data);
            _data = zkApi.getData(path, new WatcherApi());
        }
        return new Result(_data);
    }

    @GetMapping("/delete")
    public Result delete(String path) {
        Stat stat = zkApi.exists(path, false);
        boolean flag = false;
        if (stat != null) {
            flag = zkApi.delNode(path);
        }
        return new Result(flag);
    }


}
