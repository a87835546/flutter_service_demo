package com.yicen.flutter_service_demo.mapper;

import com.yicen.flutter_service_demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("zhansan");
        user.setId(1);
//        user.setPassword("123456");
        mapper.insert(user);
    }
}