package com.hgw.search.service;

import com.hgw.search.entity.AppInfo;

import java.util.List;

public interface AppInfoService {

    List<AppInfo> getAppInfo();

    Object findAppInfo(AppInfo appInfo);
}
