package com.yicen.flutter_service_demo.controller.payment;

import com.yicen.flutter_service_demo.controller.payment.service.impl.PaymentServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Api("支付的接口")
@RequestMapping("pay")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl service;


    @GetMapping("checkBalance")
    public Result checkBalance(){
        Map<String ,String> map = new HashMap();
        map.put("balance","100");
        return Result.ok(map);
    }
}
