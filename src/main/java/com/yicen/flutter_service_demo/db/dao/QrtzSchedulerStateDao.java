package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzSchedulerState;
import com.yicen.flutter_service_demo.db.vo.QrtzSchedulerStateKey;

public interface QrtzSchedulerStateDao {
    int deleteByPrimaryKey(QrtzSchedulerStateKey key);

    int insert(QrtzSchedulerState record);

    int insertSelective(QrtzSchedulerState record);

    QrtzSchedulerState selectByPrimaryKey(QrtzSchedulerStateKey key);

    int updateByPrimaryKeySelective(QrtzSchedulerState record);

    int updateByPrimaryKeyWithBLOBs(QrtzSchedulerState record);
}