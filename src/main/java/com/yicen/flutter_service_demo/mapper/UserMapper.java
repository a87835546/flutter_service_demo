package com.yicen.flutter_service_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> test();
    List<User>  selectAll();
}
