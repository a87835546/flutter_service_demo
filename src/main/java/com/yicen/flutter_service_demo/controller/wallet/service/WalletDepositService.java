package com.yicen.flutter_service_demo.controller.wallet.service;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositType;

import java.util.List;

public interface WalletDepositService {

    List<WalletDepositType> getDepositStyle();
    WalletDepositType test();
}
