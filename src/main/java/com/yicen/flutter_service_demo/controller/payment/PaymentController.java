package com.yicen.flutter_service_demo.controller.payment;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.payment.entity.PaymentVo;
import com.yicen.flutter_service_demo.controller.payment.service.impl.PaymentServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @NeedLogin
    public Result checkBalance(HttpServletRequest request){
        User user = JwtUtil.getUser(request.getHeader("token"));
        PaymentVo paymentVo = service.queryByUserId(user.getId().toString());
        Map<String ,String> map = new HashMap();
        map.put("balance",paymentVo.getBalance().toString());
        return Result.ok(map);
    }

    @GetMapping("updateBalance")
    @NeedLogin
    public Result updateBalance(HttpServletRequest request){
        Map<String ,String> map = new HashMap();
        map.put("balance","100");
        return Result.ok(map);
    }

}
