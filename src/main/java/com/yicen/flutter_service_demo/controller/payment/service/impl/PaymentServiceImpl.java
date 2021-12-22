package com.yicen.flutter_service_demo.controller.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicen.flutter_service_demo.controller.payment.entity.PaymentVo;
import com.yicen.flutter_service_demo.controller.payment.mapper.PaymentMapper;
import com.yicen.flutter_service_demo.controller.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    public PaymentVo withdraw(String userId, BigDecimal amount) {
        PaymentVo paymentVo = paymentMapper.queryByUserId(userId);
        QueryWrapper<PaymentVo> wrapper = new QueryWrapper<>();
        BigDecimal balance = paymentVo.getBalance();
        balance.subtract(amount);
        paymentVo.setBalance(balance);
        int update = paymentMapper.update(paymentVo, wrapper);
        return update>0 ? paymentMapper.selectOne(wrapper):null;
    }

    @Override
    public PaymentVo deposit(String userId,BigDecimal amount) {
        PaymentVo paymentVo = paymentMapper.queryByUserId(userId);
        if (paymentVo == null){
            PaymentVo vo = new PaymentVo();
            vo.setBalance(amount);
            vo.setUserId(Integer.valueOf(userId));
            int insert = paymentMapper.insert(vo);
            return insert > 0 ? paymentMapper.queryByUserId(userId):null;
        }else {
            QueryWrapper<PaymentVo> wrapper = new QueryWrapper<>();
            BigDecimal balance = paymentVo.getBalance();
            balance = balance.add(amount);
            paymentVo.setBalance(balance);
            int update = paymentMapper.update(paymentVo, wrapper);
            return update > 0 ? paymentMapper.selectOne(wrapper) : null;
        }
    }

    @Override
    public PaymentVo queryByUserId(String userId) {
        return paymentMapper.queryByUserId(userId);
    }

}
