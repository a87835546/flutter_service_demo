package com.yicen.flutter_service_demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yicen.flutter_service_demo.controller.user.UserController;
import com.yicen.flutter_service_demo.entity.UserDo;
import com.yicen.flutter_service_demo.utils.FastDfsCommon;
import com.yicen.flutter_service_demo.utils.RedisUtil;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private static String ip_home = "192.168.55.131";
    public static InetSocketAddress address = new InetSocketAddress(ip_home, 22122);
    public static InetSocketAddress store_address = new InetSocketAddress(ip_home, 22122);

    public static final String DEFAULT_STORAGE_IP = ip_home;

    @Autowired
    private UserController userController;

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


    }
    @Test
    void testUpload(){


    }
}