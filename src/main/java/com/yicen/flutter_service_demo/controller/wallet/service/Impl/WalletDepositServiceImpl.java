package com.yicen.flutter_service_demo.controller.wallet.service.Impl;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositType;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletDepositTypeMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletDepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WalletDepositServiceImpl implements WalletDepositService {

    @Autowired
    private WalletDepositTypeMapper walletDepositTypeMapper;

    @Override
    public List<WalletDepositType> getDepositStyle() {
        return walletDepositTypeMapper.selectAll();
    }

    @Override
    public WalletDepositType test() {
        return walletDepositTypeMapper.test();
    }

}
