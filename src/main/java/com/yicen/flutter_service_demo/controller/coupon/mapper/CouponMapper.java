package com.yicen.flutter_service_demo.controller.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.controller.coupon.entity.CouponVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CouponMapper extends BaseMapper<CouponVo> {

    @Select("select * from coupon")
    List<CouponVo> queryAll();
}