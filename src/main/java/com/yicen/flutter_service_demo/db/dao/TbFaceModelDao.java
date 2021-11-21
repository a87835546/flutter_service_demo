package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbFaceModel;
import com.yicen.flutter_service_demo.db.vo.TbFaceModelKey;

public interface TbFaceModelDao {
    int deleteByPrimaryKey(TbFaceModelKey key);

    int insert(TbFaceModel record);

    int insertSelective(TbFaceModel record);

    TbFaceModel selectByPrimaryKey(TbFaceModelKey key);

    int updateByPrimaryKeySelective(TbFaceModel record);

    int updateByPrimaryKeyWithBLOBs(TbFaceModel record);
}