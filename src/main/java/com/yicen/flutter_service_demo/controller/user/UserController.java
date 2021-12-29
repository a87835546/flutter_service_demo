package com.yicen.flutter_service_demo.controller.user;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.payment.service.impl.PaymentServiceImpl;
import com.yicen.flutter_service_demo.controller.user.entity.LoginByMobilePo;
import com.yicen.flutter_service_demo.controller.user.entity.ModifyAvatarPo;
import com.yicen.flutter_service_demo.entity.*;
import com.yicen.flutter_service_demo.entity.vo.TbRegisterUserVo;
import com.yicen.flutter_service_demo.services.impl.UserServiceImpl;
import com.yicen.flutter_service_demo.utils.FastDfsCommon;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
@Api("用户管理")
@Slf4j
public class UserController {

   static final private String kUserRedisPrefix = "userinfo:";

    @Resource
    private UserServiceImpl userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private FastDfsCommon fastDfsCommon;

    @Autowired
    private PaymentServiceImpl paymentService;

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
    @ApiOperation("注册用户，使用用户名密码")
    public Result<Map> createNewUser(@NotNull @RequestBody UserDo userDo){
        log.info(userDo.toString());
        User user = userService.queryByUsername(userDo.getUsername());
        if (user == null){
            user = new User();
            BeanUtils.copyProperties(userDo,user);
            User add = userService.add(user);
            String token = JwtUtil.getToken(add);
            JSONObject jsonObject = JSONObject.fromObject(add);
            jsonObject.put("token",token);
            redisUtil.set(add.getUsername(),token);
            JSONObject jsonObject1 = JSONObject.fromObject(add);
            redisUtil.set(kUserRedisPrefix+add.getUsername(), String.valueOf(jsonObject1));
            Boolean insert = paymentService.insert(add.getId().toString());
            log.info("添加用户的钱包成功---->>>>> {}",insert);
            return Result.ok(jsonObject);
        }else{
            return Result.error("此用户已经注册过");
        }
    }

    @PostMapping("registerByPhone")
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
    @ApiOperation("登录用户，使用用户名密码")
    public Result<User> queryUserByName(@NotNull @RequestBody UserDo userDo){
        User user = userService.queryByUsername(userDo.getUsername());
        if (user != null){
            if (!user.getPassword().equals(userDo.getPassword()))
            {
                return Result.error(201,"用户名或者密码错误");
            }else {
                String token = JwtUtil.getToken(user);
                redisUtil.set(user.getUsername(),token);
                JSONObject jsonObject1 = JSONObject.fromObject(user);
                redisUtil.set(kUserRedisPrefix+user.getUsername(), String.valueOf(jsonObject1));

                JSONObject jsonObject = JSONObject.fromObject(user);
                jsonObject.put("token",token);
                return Result.ok(jsonObject);
            }
        }else{
            return Result.ok("用户没有注册，请先注册");
        }
    }

    @GetMapping("queryUserInfo")
    @ApiOperation("查询用户消息")
    @NeedLogin
    public Result<User> queryUserByUserName(HttpServletRequest request, @RequestParam(required = false) String username){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.queryById(user.getId());
        JSONObject jsonObject = JSONObject.fromObject(user1);
        jsonObject.put("token",request.getHeader("token"));
        jsonObject.remove("password");
        return Result.ok(jsonObject);
    }

    @PostMapping("loginByPhone")
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

    @PostMapping("modifyAvatar")
    @ApiOperation("修改用户头像")
    public Result modifyAvatar(HttpServletRequest request,@RequestBody ModifyAvatarPo po){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyAvatar(user.getUsername(), po.getUrl());
        return user1.equals(null)?Result.error("修改用头像失败"):Result.ok(user1);
    }


    @PostMapping("modifyNickname")
    @ApiOperation("修改用户nickname")
    public Result modifyNickname(HttpServletRequest request,@RequestBody JSONObject json){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyUsername(user.getUsername(), json.get("nickname").toString());
        return user1.equals(null)?Result.error("修改用昵称失败"):Result.ok(user1);
    }

    @PostMapping("modifyGender")
    @ApiOperation("修改用户性别 --- gender")
    public Result modifyGender(HttpServletRequest request, @RequestBody() JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyGender(user.getUsername(),
                (Boolean) object.get("gender"));
        return user1.equals(null)?Result.error("修改性别失败"):Result.ok(user1);
    }

    @PostMapping("modifyPhone")
    @ApiOperation("修改用户手机 --- phone")
    public Result modifyPhone(HttpServletRequest request,@RequestBody JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyPhone(user.getUsername(), object.get("phone").toString());
        return user1.equals(null)?Result.error("修改手机失败"):Result.ok(user1);
    }

    @PostMapping("modifyPwd")
    @ApiOperation("修改用户密码 ---- 必须 传入 password")
    public Result modifyPwd(HttpServletRequest request,@RequestBody JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyPwd(user.getUsername(), object.get("password").toString());
        return user1.equals(null)?Result.error("修改密码失败"):Result.ok(user1);
    }

    @PostMapping("modifyEmail")
    @ApiOperation("修改用户密码 ---- 必须 传入 password")
    public Result modifyEmail(HttpServletRequest request,@RequestBody JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyPwd(user.getUsername(), object.get("email").toString());
        return user1.equals(null)?Result.error("修改邮箱失败"):Result.ok(user1);
    }
    @PostMapping("query")
    @NeedLogin
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


    public Integer getUserIdByUsername(String username){
        String s = redisUtil.get(kUserRedisPrefix + username);
        User user = (User)JSONObject.toBean(JSONObject.fromObject(s), User.class);

        return user.getId();
    }

    public User getUserByUsername(String username){
        String s = redisUtil.get(kUserRedisPrefix + username);
        User user = (User)JSONObject.toBean(JSONObject.fromObject(s), User.class);
        return user;
    }



    @RequestMapping(value={"/upload"}, method=RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String extName = "";
        String fileName = "";
        String originalFilename = file.getOriginalFilename();
        log.info("original name {}",originalFilename);
        if(originalFilename.contains(".")) {
            //拆分文件路径
            String[] fileArray = originalFilename.split("\\.");
            //获取文件扩展名

            extName = fileArray[fileArray.length-1];
            //获取文件名
            fileName = fileArray[0];
            log.info("file array ----->>>>> {} ext name --->>> {}",fileArray,extName);
        }else {
            fileName = originalFilename;
        }
        byte[] bytes = null;
        try {
            bytes = file.getBytes(); //将文件转换成字节流形式
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用上传文件的具体方法
        String fileId= FastDfsCommon.upload(bytes,extName);
        ResourceBean resourceBean = new ResourceBean(fileId, fileName);
        log.info("file id :" + fileId + "file name {}",fileName);
        log.info("ext name {}",extName);
        return Result.ok(resourceBean);
    }
}
