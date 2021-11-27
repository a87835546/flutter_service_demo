package com.yicen.flutter_service_demo.controller.user;

import com.yicen.flutter_service_demo.controller.user.entity.LoginByMobilePo;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.TbUser;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.entity.UserDo;
import com.yicen.flutter_service_demo.entity.vo.TbRegisterUserVo;
import com.yicen.flutter_service_demo.services.impl.UserServiceImpl;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
@Api("用户管理")
@Slf4j
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
        log.info("test2 request");
    }

    @GetMapping("test3")
    Result test3(@RequestParam(value = "username", required = true) String username,
                 @RequestParam(value = "password", required = true) String password,
                 HttpServletRequest request,
                 HttpServletResponse response
    ) {
        log.info("username:" + username + "\n password:" + password);
        log.info("request :" + request + "response:" + response);

        return Result.ok();
    }

    @PostMapping("test4")
    Result test4(@RequestBody UserDo userDo, HttpServletRequest request) {
        Result result = new Result<UserDo>();
        result.setData(userDo);
        log.info("requst :" + request);
        return Result.ok(userDo);
    }

    @GetMapping("test5")
    Result<List<User>> test5(){
        List<User> users = userService.queryAll();
        return Result.ok(users);
    }

    @PostMapping("register")
    public Result<User> createNewUser(@NotNull @RequestBody UserDo userDo){
        log.info(userDo.toString());
        User user = userService.queryByUsername(userDo.getUsername());
        if (user == null){
            user = new User();
            BeanUtils.copyProperties(userDo,user);
            User add = userService.add(user);
            return Result.ok(add);
        }else{
            return Result.error("此用户已经注册过");
        }
    }

    @PostMapping("login")
    public Result<User> queryUserByName(@NotNull @RequestBody UserDo userDo){
        User user = userService.queryByUsername(userDo.getUsername());
        if (user == null){
            User user1 = userService.queryByUsername(userDo.getUsername());
            if (user1 == null || !user1.getPassword().equals(userDo.getPassword()))
            {
                return Result.error(201,"用户名或者密码错误");
            }else {
                return Result.ok(user1);
            }
        }else{
            return Result.error("此用户已经注册过");
        }
    }

    @PostMapping("loginByPhone")
    public Result<User> loginByPhone(@RequestBody LoginByMobilePo po){
        log.info("user mobile login" + po);
        User user = userService.queryByPhone(po.getMobile());
        if (user != null){
            // TODO: 2021/11/28
            /// 判断验证码 是否正确。。。。
            return Result.ok(user);
        }else{
            return Result.error(202,"此用户没有注册过");
        }
    }

    @PostMapping("query")
    public Result<User> queryUSerById(){
        User user = userService.test();
        log.info(String.valueOf(user));
        return Result.ok(user);
    }

    @PostMapping("tb/register")
    Result<TbUser> registerUser(@RequestBody TbRegisterUserVo vo){
        TbUser user = userService.registerUser(vo);
        return Result.ok(user);
    }
}
