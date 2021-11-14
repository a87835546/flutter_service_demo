package com.yicen.flutter_service_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> test();
    List<User>  selectAll();

    @Override
    int insert(User entity);

}
