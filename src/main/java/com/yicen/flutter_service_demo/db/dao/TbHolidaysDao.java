package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbHolidays;
import com.yicen.flutter_service_demo.db.vo.TbHolidaysKey;

public interface TbHolidaysDao {
    int deleteByPrimaryKey(TbHolidaysKey key);

    int insert(TbHolidays record);

    int insertSelective(TbHolidays record);

    TbHolidays selectByPrimaryKey(TbHolidaysKey key);

    int updateByPrimaryKeySelective(TbHolidays record);

    int updateByPrimaryKeyWithBLOBs(TbHolidays record);
}