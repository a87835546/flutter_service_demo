package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbUser;
import com.yicen.flutter_service_demo.db.vo.TbUserKey;

public interface TbUserDao {
    int deleteByPrimaryKey(TbUserKey key);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(TbUserKey key);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKeyWithBLOBs(TbUser record);
}