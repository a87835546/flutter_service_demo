package com.yicen.flutter_service_demo.controller;

import com.yicen.flutter_service_demo.entity.UserDo;
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

    @Test
    public void name() {
        UserDo userDo = new UserDo();
        userDo.setUsername("lisi");
        userDo.setPassword("123456");
        userController.createNewUser(userDo);
    }

    @Test
    public void test1() {
        userController.queryUSerById();
    }
}