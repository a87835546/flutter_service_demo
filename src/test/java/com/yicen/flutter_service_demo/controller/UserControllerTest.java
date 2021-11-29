package com.yicen.flutter_service_demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yicen.flutter_service_demo.controller.user.UserController;
import com.yicen.flutter_service_demo.entity.UserDo;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

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
}