package com.hgw.search.controller;

import com.hgw.search.kafka.KafkaProducer;
import com.hgw.search.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/send")
    public Result send() {
        kafkaProducer.send("你好！");
        return new Result();
    }
}
