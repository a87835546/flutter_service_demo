package com.yicen.flutter_service_demo.controller.wallet.service;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletInsertChannelVo;

import java.util.List;

public interface WalletDepositChannelService {

    List<WalletDepositChannelVo> getDepositStyle();
    WalletDepositChannelVo test();
    int  insert(WalletDepositChannelVo vo);
}
