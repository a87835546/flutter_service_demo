package com.yicen.flutter_service_demo.controller;

import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("test")
    List<User> test() {
        return userMapper.test();
    }

    @GetMapping("test1")
    Map<String, String> test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test1", "12323");
        return map;
    }

    @GetMapping("test2")
    void test2() {
        System.out.println("test2 request");
    }

    @GetMapping("test3")
    Result test3(@RequestParam(value = "username",required = true) String username,
                 @RequestParam(value = "password",required = true) String password) {
        System.out.printf("username:"+username+"\n password:"+password);
        return Result.ok();
    }
}
