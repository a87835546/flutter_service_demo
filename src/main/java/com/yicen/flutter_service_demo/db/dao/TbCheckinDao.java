package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbCheckin;
import com.yicen.flutter_service_demo.db.vo.TbCheckinKey;

public interface TbCheckinDao {
    int deleteByPrimaryKey(TbCheckinKey key);

    int insert(TbCheckin record);

    int insertSelective(TbCheckin record);

    TbCheckin selectByPrimaryKey(TbCheckinKey key);

    int updateByPrimaryKeySelective(TbCheckin record);

    int updateByPrimaryKeyWithBLOBs(TbCheckin record);
}