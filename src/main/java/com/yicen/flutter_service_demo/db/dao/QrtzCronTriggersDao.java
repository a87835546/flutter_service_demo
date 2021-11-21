package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzCronTriggers;
import com.yicen.flutter_service_demo.db.vo.QrtzCronTriggersKey;

public interface QrtzCronTriggersDao {
    int deleteByPrimaryKey(QrtzCronTriggersKey key);

    int insert(QrtzCronTriggers record);

    int insertSelective(QrtzCronTriggers record);

    QrtzCronTriggers selectByPrimaryKey(QrtzCronTriggersKey key);

    int updateByPrimaryKeySelective(QrtzCronTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzCronTriggers record);
}