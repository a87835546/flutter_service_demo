package com.yicen.flutter_service_demo.controller.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yicen.flutter_service_demo.controller.coupon.entity.CouponVo;

import java.util.List;

public interface CouponService {
    public IPage<CouponVo> queryAll(int current,int size);
    public Integer insert(CouponVo vo);
}
