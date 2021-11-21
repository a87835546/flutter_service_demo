package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbAction;
import com.yicen.flutter_service_demo.db.vo.TbActionKey;

public interface TbActionDao {
    int deleteByPrimaryKey(TbActionKey key);

    int insert(TbAction record);

    int insertSelective(TbAction record);

    TbAction selectByPrimaryKey(TbActionKey key);

    int updateByPrimaryKeySelective(TbAction record);

    int updateByPrimaryKeyWithBLOBs(TbAction record);
}