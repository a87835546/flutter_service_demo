package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbCity;
import com.yicen.flutter_service_demo.db.vo.TbCityKey;

public interface TbCityDao {
    int deleteByPrimaryKey(TbCityKey key);

    int insert(TbCity record);

    int insertSelective(TbCity record);

    TbCity selectByPrimaryKey(TbCityKey key);

    int updateByPrimaryKeySelective(TbCity record);

    int updateByPrimaryKeyWithBLOBs(TbCity record);
}