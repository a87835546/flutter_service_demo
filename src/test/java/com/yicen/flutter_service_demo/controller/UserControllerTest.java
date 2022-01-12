package com.yicen.flutter_service_demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yicen.flutter_service_demo.controller.user.UserController;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.entity.UserDo;
import com.yicen.flutter_service_demo.services.impl.UserServiceImpl;
import com.yicen.flutter_service_demo.utils.FastDfsCommon;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.hashids.Hashids;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {

    private static String ip_home = "192.168.55.131";
    public static InetSocketAddress address = new InetSocketAddress(ip_home, 22122);
    public static InetSocketAddress store_address = new InetSocketAddress(ip_home, 22122);

    public static final String DEFAULT_STORAGE_IP = ip_home;

    @Autowired
    private UserController userController;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FastDfsCommon fastDfsCommon;

    @Autowired
    private  RedisUtil util;

    @Test
    public void name() throws JsonProcessingException {
        UserDo userDo = new UserDo();
        userDo.setUsername("lisi");
        userDo.setPassword("123456");
        userController.createNewUser(userDo);
    }

    @Test
    public void test1() {
        userController.queryUSerById();
    }


    @Test
    public  void  test2(){
        util.set("name","zhangsan");
    }

    @Test
    public void  test3(){
//
//        Hashids hashids = new Hashids("THIS IS MY SLOT",6);
//        for (long i = 0L; i < 76; i++) {
//            String inviteCode = getInviteCode(i);
//            log.info("invite code ; {}",inviteCode);
//            log.info("hash id invite code --->>> {}", hashids.encode(i));
//
//        }
        List<User> users = userService.queryAll();
        for (User user:
             users) {
            if (null == user.getInviteCode()){
                userService.createCode(user.getId());
            }
        }
    }
    @Test
    public void testUpload(){
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        userController.testSendMq("123 --- >>{}"+format,"123");

    }

    String str = "Q7VBNYR1HSCUEODIX3K95F48L0P6GAMTWJZ2";
    public String getInviteCode(long id){
        int key = (int) (id % 36);
        char[] cs = new char[6];
        cs[0] = str.charAt(key);
        id = id / 36;
        long code = 1_000_000 + id * 13;
        for (int i = 1; i < 6; i++) {
            cs[i] = str.charAt((int) (code % 36));
            code = code / 36;
        }
        return new String(cs);
    }

    @Test
    public void  test8(){
        for (int i = 0; i < 10000; i++) {
            userController.testSendMq(i+"","");
        }
    }
}