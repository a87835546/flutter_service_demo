package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbModule;
import com.yicen.flutter_service_demo.db.vo.TbModuleKey;

public interface TbModuleDao {
    int deleteByPrimaryKey(TbModuleKey key);

    int insert(TbModule record);

    int insertSelective(TbModule record);

    TbModule selectByPrimaryKey(TbModuleKey key);

    int updateByPrimaryKeySelective(TbModule record);

    int updateByPrimaryKeyWithBLOBs(TbModule record);
}