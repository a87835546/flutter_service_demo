package com.yicen.flutter_service_demo.controller.payment.service;

import com.yicen.flutter_service_demo.controller.payment.entity.PaymentVo;

import java.math.BigDecimal;

public interface PaymentService {
    public PaymentVo withdraw(String userId, BigDecimal amount);
    public PaymentVo deposit(String userId,BigDecimal amount);
    public PaymentVo queryByUserId(String userId);
    public Boolean insert(String userId);
}
