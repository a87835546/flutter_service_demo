package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzTriggers;
import com.yicen.flutter_service_demo.db.vo.QrtzTriggersKey;

public interface QrtzTriggersDao {
    int deleteByPrimaryKey(QrtzTriggersKey key);

    int insert(QrtzTriggers record);

    int insertSelective(QrtzTriggers record);

    QrtzTriggers selectByPrimaryKey(QrtzTriggersKey key);

    int updateByPrimaryKeySelective(QrtzTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzTriggers record);
}