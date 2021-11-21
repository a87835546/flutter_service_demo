package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbPermission;
import com.yicen.flutter_service_demo.db.vo.TbPermissionKey;

public interface TbPermissionDao {
    int deleteByPrimaryKey(TbPermissionKey key);

    int insert(TbPermission record);

    int insertSelective(TbPermission record);

    TbPermission selectByPrimaryKey(TbPermissionKey key);

    int updateByPrimaryKeySelective(TbPermission record);

    int updateByPrimaryKeyWithBLOBs(TbPermission record);
}