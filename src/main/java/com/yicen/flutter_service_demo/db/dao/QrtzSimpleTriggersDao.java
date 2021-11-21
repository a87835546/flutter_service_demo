package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzSimpleTriggers;
import com.yicen.flutter_service_demo.db.vo.QrtzSimpleTriggersKey;

public interface QrtzSimpleTriggersDao {
    int deleteByPrimaryKey(QrtzSimpleTriggersKey key);

    int insert(QrtzSimpleTriggers record);

    int insertSelective(QrtzSimpleTriggers record);

    QrtzSimpleTriggers selectByPrimaryKey(QrtzSimpleTriggersKey key);

    int updateByPrimaryKeySelective(QrtzSimpleTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzSimpleTriggers record);
}