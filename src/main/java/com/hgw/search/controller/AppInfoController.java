package com.hgw.search.controller;

import com.hgw.search.entity.AppInfo;
import com.hgw.search.service.AppInfoService;
import com.hgw.search.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {

    @Autowired
    AppInfoService appInfoService;

    @GetMapping("/get")
    public Result get() {
        return new Result(appInfoService.getAppInfo());
    }

    @GetMapping("/find")
    public Result find(AppInfo appInfo) {
        return new Result(appInfoService.findAppInfo(appInfo));
    }
}
