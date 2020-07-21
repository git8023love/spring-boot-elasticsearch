package com.hgw.search.dao;

import com.hgw.search.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppInfoDao extends JpaRepository<AppInfo, Integer> {
}
