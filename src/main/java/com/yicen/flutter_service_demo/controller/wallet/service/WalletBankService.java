package com.yicen.flutter_service_demo.controller.wallet.service;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletBankVo;

import java.util.List;

public interface WalletBankService {

    List<WalletBankVo> getBankList(String userId);
    int insertBank(WalletBankVo bank);
    int deleteBank(Integer userId);
    WalletBankVo queryByCardNumber(String number);
    int updateDelete(String cardNum);
}
