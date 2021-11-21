package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbWorkday;
import com.yicen.flutter_service_demo.db.vo.TbWorkdayKey;

public interface TbWorkdayDao {
    int deleteByPrimaryKey(TbWorkdayKey key);

    int insert(TbWorkday record);

    int insertSelective(TbWorkday record);

    TbWorkday selectByPrimaryKey(TbWorkdayKey key);

    int updateByPrimaryKeySelective(TbWorkday record);

    int updateByPrimaryKeyWithBLOBs(TbWorkday record);
}