package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbRole;
import com.yicen.flutter_service_demo.db.vo.TbRoleKey;

public interface TbRoleDao {
    int deleteByPrimaryKey(TbRoleKey key);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    TbRole selectByPrimaryKey(TbRoleKey key);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKeyWithBLOBs(TbRole record);
}