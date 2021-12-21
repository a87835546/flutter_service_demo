package com.yicen.flutter_service_demo.controller.wallet;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletDepositTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
class DepositControllerTest {
    @Autowired
    private DepositController depositController;

    @Autowired
    private  WalletDepositTypeMapper mapper;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void depositStyle() {
    }

    @Test
    void test1() {
        log.info(mapper.selectAll() + "");
    }

    @Test
    void  test2(){

        log.info( "");
    }
}