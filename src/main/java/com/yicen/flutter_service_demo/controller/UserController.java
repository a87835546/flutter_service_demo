package com.yicen.flutter_service_demo.controller;

import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.entity.UserDo;
import com.yicen.flutter_service_demo.imp.UserServiceImpl;
import com.yicen.flutter_service_demo.mapper.UserMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
@Api("用户管理")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @GetMapping("test")
    Result<User> test() {
        return Result.ok(userService.test());

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
    Result test3(@RequestParam(value = "username", required = true) String username,
                 @RequestParam(value = "password", required = true) String password,
                 HttpServletRequest request,
                 HttpServletResponse response
    ) {
        System.out.printf("username:" + username + "\n password:" + password);
        System.out.printf("requst :" + request + "response:" + response);

        return Result.ok();
    }

    @PostMapping("test4")
    Result test4(@RequestBody UserDo userDo, HttpServletRequest request) {
        Result result = new Result<UserDo>();
        result.setData(userDo);
        System.out.println("requst :" + request);
        return Result.ok(userDo);
    }

    @GetMapping("test5")
    Result<List<User>> test5(){
        List<User> users = userService.queryAll();
        return Result.ok(users);
    }

    @PostMapping("register")
    Result<User> createNewUser(@RequestBody UserDo userDo){
        User user = new User();
        BeanUtils.copyProperties(userDo,user);
        return Result.ok(userService.add(user));
    }

    @PostMapping("query")
    Result<User> queryUSerById(){
        User user = userService.test();
        return Result.ok(user);
    }
}
