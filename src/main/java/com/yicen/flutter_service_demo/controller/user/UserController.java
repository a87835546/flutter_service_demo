package com.yicen.flutter_service_demo.controller.user;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.config.RabbitMqConfig;
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
import net.bytebuddy.agent.builder.AgentBuilder;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@RequestMapping("/user/")
@Api("????????????")
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

    @Autowired
    private AmqpTemplate rabbitTemplate;


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
    @ApiOperation("????????????????????????????????????")
    public Result<Map> createNewUser(@NotNull @RequestBody UserDo userDo) {
        log.info(userDo.toString());
        User user = userService.queryByUsername(userDo.getUsername());
        if (user == null){
            user = new User();
            BeanUtils.copyProperties(userDo,user);
            User add = userService.add(user);
            log.info("insert user info {}",add);
            String token = JwtUtil.getToken(add);
            JSONObject jsonObject = JSONObject.fromObject(add);
            jsonObject.put("token",token);
            JSONObject jsonObject1 = JSONObject.fromObject(add);
            redisUtil.set(kUserRedisPrefix+add.getUsername(), String.valueOf(jsonObject1));
            Boolean insert = paymentService.insert(add.getId().toString());
            log.info("???????????????????????????---->>>>> {}",insert);
            return Result.ok(jsonObject);
        }else{
            return Result.error("????????????????????????");
        }
    }

    @PostMapping("registerByPhone")
    @ApiOperation("????????????????????????????????????")
    public Result<User> registerByPhone(@NotNull @RequestBody LoginByMobilePo po){
        log.info(po.toString());
        User user = userService.queryByUsername(po.getMobile());
        if (user == null){
            user = new User();
            BeanUtils.copyProperties(po,user);
            User add = userService.add(user);
            return Result.ok(add);
        }else{
            return Result.error("????????????????????????");
        }
    }

    @PostMapping("login")
    @ApiOperation("????????????????????????????????????")
    public Result<User> queryUserByName(@NotNull @RequestBody UserDo userDo){
        User user = userService.queryByUsername(userDo.getUsername());
        if (user != null){
            if (!user.getPassword().equals(userDo.getPassword()))
            {
                return Result.error(201,"???????????????????????????");
            }else {
                String token = JwtUtil.getToken(user);
                JSONObject jsonObject1 = JSONObject.fromObject(user);
                redisUtil.set(kUserRedisPrefix+user.getUsername(), String.valueOf(jsonObject1));

                JSONObject jsonObject = JSONObject.fromObject(user);
                jsonObject.put("token",token);
                return Result.ok(jsonObject);
            }
        }else{
            return Result.ok("?????????????????????????????????");
        }
    }

    @GetMapping("queryUserInfo")
    @ApiOperation("??????????????????")
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
    @ApiOperation("??????????????????????????????????????????")
    public Result<User> loginByPhone(@RequestBody LoginByMobilePo po){
        log.info("user mobile login" + po);
        User user = userService.queryByPhone(po.getMobile());
        if (user != null){
            // TODO: 2021/11/28
            /// ??????????????? ????????????????????????
            return Result.ok(user);
        }else{
            return Result.error(202,"????????????????????????");
        }
    }

    @PostMapping("modifyAvatar")
    @ApiOperation("??????????????????")
    public Result modifyAvatar(HttpServletRequest request,@RequestBody ModifyAvatarPo po){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyAvatar(user.getUsername(), po.getUrl());
        return user1.equals(null)?Result.error("?????????????????????"):Result.ok(user1);
    }


    @PostMapping("modifyNickname")
    @ApiOperation("????????????nickname")
    public Result modifyNickname(HttpServletRequest request,@RequestBody JSONObject json){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyUsername(user.getUsername(), json.get("nickname").toString());
        return user1.equals(null)?Result.error("?????????????????????"):Result.ok(user1);
    }

    @PostMapping("modifyGender")
    @ApiOperation("?????????????????? --- gender")
    public Result modifyGender(HttpServletRequest request, @RequestBody() JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyGender(user.getUsername(),
                (Boolean) object.get("gender"));
        return user1.equals(null)?Result.error("??????????????????"):Result.ok(user1);
    }

    @PostMapping("modifyPhone")
    @ApiOperation("?????????????????? --- phone")
    public Result modifyPhone(HttpServletRequest request,@RequestBody JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyPhone(user.getUsername(), object.get("phone").toString());
        return user1.equals(null)?Result.error("??????????????????"):Result.ok(user1);
    }

    @PostMapping("modifyPwd")
    @ApiOperation("?????????????????? ---- ?????? ?????? password")
    public Result modifyPwd(HttpServletRequest request,@RequestBody JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyPwd(user.getUsername(), object.get("password").toString());
        return user1.equals(null)?Result.error("??????????????????"):Result.ok(user1);
    }

    @PostMapping("modifyEmail")
    @ApiOperation("?????????????????? ---- ?????? ?????? password")
    public Result modifyEmail(HttpServletRequest request,@RequestBody JSONObject object){
        User user = JwtUtil.getUser(request.getHeader("token"));
        User user1 = userService.modifyPwd(user.getUsername(), object.get("email").toString());
        return user1.equals(null)?Result.error("??????????????????"):Result.ok(user1);
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
            //??????????????????
            String[] fileArray = originalFilename.split("\\.");
            //?????????????????????

            extName = fileArray[fileArray.length-1];
            //???????????????
            fileName = fileArray[0];
            log.info("file array ----->>>>> {} ext name --->>> {}",fileArray,extName);
        }else {
            fileName = originalFilename;
        }
        byte[] bytes = null;
        try {
            bytes = file.getBytes(); //?????????????????????????????????
        } catch (IOException e) {
            e.printStackTrace();
        }
        //?????????????????????????????????
        String fileId= FastDfsCommon.upload(bytes,extName);
        ResourceBean resourceBean = new ResourceBean(fileId, fileName);
        log.info("file id :" + fileId + "file name {}",fileName);
        log.info("ext name {}",extName);
        return Result.ok(resourceBean);
    }

    @GetMapping("test9")
    @ApiOperation("??????mq --->>>????????????")
    public void testSendMq(@RequestParam String msg,@RequestParam String key){
        /**
         *  ???????????????routing key????????????????????????exchange
         *  ??????????????????????????? ??????????????? ???????????????key??? ??????????????????????????????????????????key??????
         */
        rabbitTemplate.convertAndSend(RabbitMqConfig.kTopicExchange,RabbitMqConfig.kRoutingKey,msg);
    }

    @GetMapping("test11")
    @ApiOperation("?????? ?????????mq --->>>????????????")
    public void test11( HttpServletRequest request,@RequestParam String msg){
        /**
         *  ???????????????routing key????????????????????????exchange
         *  ??????????????????????????? ??????????????? ???????????????key??? ??????????????????????????????????????????key??????
         */
        User user = JwtUtil.getUserByRequestServlet(request);

//        rabbitTemplate.convertAndSend(RabbitMqConfig.kTopicExchange,"user.*",msg);
        rabbitTemplate.convertAndSend(RabbitMqConfig.kTopicExchange,"user.*",user);
    }
    @GetMapping("test10")
    @ApiOperation("??????mq --->>>> ??????mq??????")
    @RabbitListener(queues = RabbitMqConfig.kQueue)
    @RabbitHandler
    public void test10(String msg){
        log.info("????????????????????????---->>>>{}",msg);
    }

    @GetMapping("test12")
    @ApiOperation("?????? ??????mq --->>>> ??????mq??????")
    @RabbitListener(queues = "register_queue")
    @RabbitHandler
    @MessageMapping("/message")
    public void test12(@Payload User user, @Headers Map<String,Object> headers, Object msg,Message message){
        log.info(user.toString());
    }

    @GetMapping("getInviteCode")
    @ApiOperation("????????????????????????")
    public Result getInviteCode(HttpServletRequest request){
        User user = JwtUtil.getUserByRequestServlet(request);
        String inviteCode = userService.getInviteCode(user.getId().longValue());
        Map map = new HashMap();
        map.put("invite",inviteCode);
        return Result.ok(map);
    }
}
