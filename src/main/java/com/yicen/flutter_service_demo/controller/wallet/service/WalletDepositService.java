package com.yicen.flutter_service_demo.controller.wallet.service;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;

import java.util.List;

public interface WalletDepositService {

    List<WalletDepositTypeVo> getDepositStyle();
    WalletDepositTypeVo test();
    List<WalletDepositChannelVo> getChannel();
}
