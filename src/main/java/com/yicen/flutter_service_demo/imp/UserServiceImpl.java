package com.yicen.flutter_service_demo.imp;

import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    UserMapper userMapper;

    public List<User> queryAll(){
        return userMapper.selectList(null);
    }

    public User test(){
        return userMapper.selectById("1");
    }

    public User add(User user){
        int i = userMapper.insert(user);
        return  userMapper.selectById(i);
    }


}
