package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.SysConfig;
import com.yicen.flutter_service_demo.db.vo.SysConfigKey;

public interface SysConfigDao {
    int deleteByPrimaryKey(SysConfigKey key);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(SysConfigKey key);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKeyWithBLOBs(SysConfig record);
}