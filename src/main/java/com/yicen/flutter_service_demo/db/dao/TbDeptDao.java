package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbDept;
import com.yicen.flutter_service_demo.db.vo.TbDeptKey;

public interface TbDeptDao {
    int deleteByPrimaryKey(TbDeptKey key);

    int insert(TbDept record);

    int insertSelective(TbDept record);

    TbDept selectByPrimaryKey(TbDeptKey key);

    int updateByPrimaryKeySelective(TbDept record);

    int updateByPrimaryKeyWithBLOBs(TbDept record);
}