package com.yicen.flutter_service_demo.controller.active.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.controller.active.entity.Active;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActiveMapper extends BaseMapper<Active> {


    public Active test();

    @Select("select * from active")
    public List<Active> queryAll();
}
