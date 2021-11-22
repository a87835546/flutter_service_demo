package com.yicen.flutter_service_demo.services;

import com.yicen.flutter_service_demo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> queryAll();
    public User test();
    public User add(User user);
}
