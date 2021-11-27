package com.yicen.flutter_service_demo.controller.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.controller.payment.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
    public Payment test();

    @Select("select * from payment where user_id = #{userId}")
    public Payment queryByUserId(String userId);
}
