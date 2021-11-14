package com.yicen.flutter_service_demo;

import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void insert(){
        User user = new User();
        user.setUsername("zhansan");
//        user.setPassword("123456");
        mapper.insert(user);
    }
}
