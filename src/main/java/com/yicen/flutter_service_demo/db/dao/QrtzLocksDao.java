package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzLocks;
import com.yicen.flutter_service_demo.db.vo.QrtzLocksKey;

public interface QrtzLocksDao {
    int deleteByPrimaryKey(QrtzLocksKey key);

    int insert(QrtzLocks record);

    int insertSelective(QrtzLocks record);

    QrtzLocks selectByPrimaryKey(QrtzLocksKey key);

    int updateByPrimaryKeySelective(QrtzLocks record);

    int updateByPrimaryKeyWithBLOBs(QrtzLocks record);
}