package com.yicen.flutter_service_demo.controller.wallet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;

import java.util.List;

public interface TransactionService {
    boolean insertRecord(WalletTransactionVo vo);
    List<WalletTransactionVo> queryListByUserName(String username);
    IPage<WalletTransactionVo> selectTransactionByUserId(String username,Integer pageSize,Integer current);

}
