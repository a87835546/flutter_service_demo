package com.yicen.flutter_service_demo.controller.wallet.service;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletBankVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletCrVo;

import java.util.List;

public interface WalletCrService  {

    public WalletCrVo queryByCrAddress(String address);
    public int updateDelete(String cardNum);
    public int insertBank(WalletCrVo walletCrVo);
    List<WalletCrVo> getCrList(String userId);

}
