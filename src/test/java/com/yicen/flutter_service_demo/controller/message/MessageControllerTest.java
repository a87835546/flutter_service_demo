package com.yicen.flutter_service_demo.controller.message;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class MessageControllerTest {

    @Autowired
    private MessageController messageController;
    @Test
    void getInfo() {
        messageController.getInfo();
    }

    @Test
    void test() {
        messageController.writeIdToRedis();
    }
}