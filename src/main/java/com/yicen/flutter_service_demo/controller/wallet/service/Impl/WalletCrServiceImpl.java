package com.yicen.flutter_service_demo.controller.wallet.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletBankVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletCrVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletCrMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletCrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WalletCrServiceImpl implements WalletCrService {

    @Autowired
    private WalletCrMapper mapper;

    @Override
    public WalletCrVo queryByCrAddress(String address) {
        QueryWrapper<WalletCrVo> wrapper = new QueryWrapper<WalletCrVo>();
        wrapper.eq("address",address);
        return mapper.selectOne(wrapper);
    }

    @Override
    public int updateDelete(String cardNum){

        QueryWrapper<WalletCrVo> wrapper = new QueryWrapper<WalletCrVo>();
        wrapper.eq("address",cardNum);
        WalletCrVo vo = new WalletCrVo();
        vo.setIsDelete(true);
        return mapper.update(vo,wrapper);
    }

    @Override
    public int insertBank(WalletCrVo walletCrVo) {
        return mapper.insert(walletCrVo);
    }

    @Override
    public List<WalletCrVo> getCrList(String userId) {
        QueryWrapper<WalletCrVo> wrapper = new QueryWrapper<WalletCrVo>();
        wrapper.eq("user_id",userId);
        return mapper.selectList(wrapper);
    }
}
