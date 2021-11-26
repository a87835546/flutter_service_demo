package com.yicen.flutter_service_demo.controller;

import com.yicen.flutter_service_demo.controller.active.ActiveController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ActiveControllerTest {

    @Autowired
    private ActiveController activeController;

    @Test
    void  test1 (){
        activeController.getActiveList(

        );
    }

    @Test
    void test2 (){
        activeController.getBannerList();
    }


    @Test
    void test3 (){
        activeController.test();
    }
    @Test
    void test4 (){
        activeController.test2();
    }
}