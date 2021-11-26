package com.yicen.flutter_service_demo.services;

import com.yicen.flutter_service_demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("UserService")
public interface UserService {
    public List<User> queryAll();
    public User test();
    public User add(User user);
}
