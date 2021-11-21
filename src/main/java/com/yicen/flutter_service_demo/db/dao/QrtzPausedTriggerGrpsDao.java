package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzPausedTriggerGrps;
import com.yicen.flutter_service_demo.db.vo.QrtzPausedTriggerGrpsKey;

public interface QrtzPausedTriggerGrpsDao {
    int deleteByPrimaryKey(QrtzPausedTriggerGrpsKey key);

    int insert(QrtzPausedTriggerGrps record);

    int insertSelective(QrtzPausedTriggerGrps record);

    QrtzPausedTriggerGrps selectByPrimaryKey(QrtzPausedTriggerGrpsKey key);

    int updateByPrimaryKeySelective(QrtzPausedTriggerGrps record);

    int updateByPrimaryKeyWithBLOBs(QrtzPausedTriggerGrps record);
}