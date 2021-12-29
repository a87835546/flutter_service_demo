package com.yicen.flutter_service_demo.controller.coupon;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.coupon.entity.CouponVo;
import com.yicen.flutter_service_demo.controller.coupon.service.impl.CouponServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("coupon/")
public class CouponController {
    @Autowired
    private CouponServiceImpl couponService;

    @GetMapping("list")
    @NeedLogin
    @ApiOperation("测试")
    public Result getAll(HttpServletRequest request,@RequestParam(required = false) int current,@RequestParam(required = false) int size){
        if (size == 0){ size =  10;}
        if (current == 0){ current =  1;}
        return Result.ok( couponService.queryAll(current,size));
    }

    @PostMapping("add")
    @NeedLogin
    @ApiOperation("添加优惠券")
    public Result insert(@RequestBody CouponVo vo){
        log.info("coupon model {}",vo);
        Integer insert = couponService.insert(vo);
        return insert > 0 ?  Result.ok("成功") : Result.error("失败");
    }
}
