package com.yicen.flutter_service_demo.controller.wallet.service;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;

import java.util.List;

public interface WalletDepositChannelService {

    List<WalletDepositChannelVo> getDepositStyle();
    WalletDepositChannelVo test();
}
