package com.yicen.flutter_service_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class AddressControllerTest {

    @Autowired
    private AddressController controller;

    @Test
    public void name() {

        controller.test();
    }

    @Test
    public void name1() {
        log.info(controller.test1().toString());
    }
}