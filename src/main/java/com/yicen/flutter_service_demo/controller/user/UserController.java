package com.yicen.flutter_service_demo.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicen.flutter_service_demo.config.NoNeedLogin;
import com.yicen.flutter_service_demo.controller.user.entity.LoginByMobilePo;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.TbUser;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.entity.UserDo;
import com.yicen.flutter_service_demo.entity.vo.TbRegisterUserVo;
import com.yicen.flutter_service_demo.services.impl.UserServiceImpl;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("test")
    @NoNeedLogin
    Result<User> test() {
        return Result.ok(userService.test());
    }

    @GetMapping("test1")
    @NoNeedLogin
    Map<String, String> test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test1", "12323");
        return map;
    }

    @GetMapping("test2")
    @NoNeedLogin
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
    @NoNeedLogin
    @ApiOperation("注册用户，使用用户名密码")
    public Result<Map> createNewUser(@NotNull @RequestBody UserDo userDo) throws JsonProcessingException {
        log.info(userDo.toString());
        User user = userService.queryByUsername(userDo.getUsername());
        if (user == null){
            user = new User();
            BeanUtils.copyProperties(userDo,user);
            User add = userService.add(user);
            String token = JwtUtil.getToken(add.getUsername());
            JSONObject jsonObject = JSONObject.fromObject(add);
            jsonObject.put("token",token);
            redisUtil.set(add.getUsername(),token);
            return Result.ok(jsonObject);
        }else{
            return Result.error("此用户已经注册过");
        }
    }

    @PostMapping("registerByPhone")
    @NoNeedLogin
    @ApiOperation("注册用户，使用用户名密码")
    public Result<User> registerByPhone(@NotNull @RequestBody LoginByMobilePo po){
        log.info(po.toString());
        User user = userService.queryByUsername(po.getMobile());
        if (user == null){
            user = new User();
            BeanUtils.copyProperties(po,user);
            User add = userService.add(user);
            return Result.ok(add);
        }else{
            return Result.error("此用户已经注册过");
        }
    }

    @PostMapping("login")
    @NoNeedLogin
    @ApiOperation("登录用户，使用用户名密码")
    public Result<User> queryUserByName(@NotNull @RequestBody UserDo userDo){
        User user = userService.queryByUsername(userDo.getUsername());
        if (user != null){
            if (!user.getPassword().equals(userDo.getPassword()))
            {
                return Result.error(201,"用户名或者密码错误");
            }else {
                String token = JwtUtil.getToken(user.getUsername());
                redisUtil.set(user.getUsername(),token);
                JSONObject jsonObject = JSONObject.fromObject(user);
                jsonObject.put("token",token);
                return Result.ok(jsonObject);
            }
        }else{
            return Result.ok("用户没有注册，请先注册");
        }
    }

    @PostMapping("loginByPhone")
    @NoNeedLogin
    @ApiOperation("登录用户，使用手机号和验证码")
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
    @NoNeedLogin
    Result<TbUser> registerUser(@RequestBody TbRegisterUserVo vo){
        TbUser user = userService.registerUser(vo);
        return Result.ok(user);
    }
}
