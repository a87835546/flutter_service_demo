package com.yicen.flutter_service_demo.controller.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yicen.flutter_service_demo.controller.coupon.entity.CouponVo;
import com.yicen.flutter_service_demo.controller.coupon.mapper.CouponMapper;
import com.yicen.flutter_service_demo.controller.coupon.service.CouponService;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public IPage<CouponVo> queryAll(int current,int size) {
        log.info("current -->>> {} size-->>> {}",current,size);
        IPage<CouponVo> page = new Page<>(current,size);
        QueryWrapper<CouponVo> wrapper = new QueryWrapper<>();
        /// mybatis plus 的排序方式
        wrapper.orderByDesc(Arrays.asList(new String[] {"create_time"}));
        IPage<CouponVo> walletTransactionVoIPage = couponMapper.selectPage(page,wrapper);
        return walletTransactionVoIPage;

    }

    @Override
    public Integer insert(CouponVo couponVo) {
        return couponMapper.insert(couponVo);
    }
}
