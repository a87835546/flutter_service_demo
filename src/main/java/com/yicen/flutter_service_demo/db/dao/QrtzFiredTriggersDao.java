package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzFiredTriggers;
import com.yicen.flutter_service_demo.db.vo.QrtzFiredTriggersKey;

public interface QrtzFiredTriggersDao {
    int deleteByPrimaryKey(QrtzFiredTriggersKey key);

    int insert(QrtzFiredTriggers record);

    int insertSelective(QrtzFiredTriggers record);

    QrtzFiredTriggers selectByPrimaryKey(QrtzFiredTriggersKey key);

    int updateByPrimaryKeySelective(QrtzFiredTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzFiredTriggers record);
}