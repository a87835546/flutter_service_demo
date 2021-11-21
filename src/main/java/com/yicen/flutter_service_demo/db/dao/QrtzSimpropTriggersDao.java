package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzSimpropTriggers;
import com.yicen.flutter_service_demo.db.vo.QrtzSimpropTriggersKey;

public interface QrtzSimpropTriggersDao {
    int deleteByPrimaryKey(QrtzSimpropTriggersKey key);

    int insert(QrtzSimpropTriggers record);

    int insertSelective(QrtzSimpropTriggers record);

    QrtzSimpropTriggers selectByPrimaryKey(QrtzSimpropTriggersKey key);

    int updateByPrimaryKeySelective(QrtzSimpropTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzSimpropTriggers record);
}