package com.hgw.search.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Configuration
public class ZookeeperConfig {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);

    @Value("${zookeeper.address}")
    private    String address;

    @Value("${zookeeper.timeout}")
    private  int timeout;

    @Bean("zkClient")
    public ZooKeeper zkClient() {
        ZooKeeper zk = null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            zk = new ZooKeeper(address, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                            countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            logger.info("init zookeeper connect await...........");
        } catch (IOException | InterruptedException e) {
            logger.error("", e);
            e.printStackTrace();
        }
        return zk;
    }
}
