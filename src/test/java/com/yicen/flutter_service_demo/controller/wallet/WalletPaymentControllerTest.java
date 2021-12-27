package com.yicen.flutter_service_demo.controller.wallet;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletDepositTypeMapper;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletTransactionMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.TransactionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
class WalletPaymentControllerTest {
    @Autowired
    private WalletPaymentController walletPaymentController;

    @Autowired
    private  WalletDepositTypeMapper mapper;

    @Autowired
    private WalletTransactionMapper transactionMapper;

    @Autowired
    private TransactionServiceImpl transactionService;
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
        Page page = new Page<WalletTransactionVo>(1,5);
        log.info( "{}",transactionMapper.selectTransactionByUserId(page));
    }
    @Test
    void test3() {
        log.info("request -->>>> {}",transactionService.selectTransactionByUserId(0,"test8891",10,1));
    }
}