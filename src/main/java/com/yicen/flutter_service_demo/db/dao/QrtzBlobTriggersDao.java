package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.QrtzBlobTriggers;
import com.yicen.flutter_service_demo.db.vo.QrtzBlobTriggersKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


@Resource
public interface QrtzBlobTriggersDao {
    int deleteByPrimaryKey(QrtzBlobTriggersKey key);

    int insert(QrtzBlobTriggers record);

    int insertSelective(QrtzBlobTriggers record);

    QrtzBlobTriggers selectByPrimaryKey(QrtzBlobTriggersKey key);

    int updateByPrimaryKeySelective(QrtzBlobTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzBlobTriggers record);
}