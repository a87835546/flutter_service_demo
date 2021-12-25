package com.yicen.flutter_service_demo.controller.wallet.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletDepositTypeMapper;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletTransactionMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.TransactionService;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletDepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private WalletTransactionMapper transactionMapper;


    @Override
    public boolean insertRecord(WalletTransactionVo vo) {
        log.info("wallet transaction model {}",vo);
        int insert = transactionMapper.insert(vo);
        return insert > 0;
    }

    @Override
    public List<WalletTransactionVo> queryListByUserName(String username) {
        QueryWrapper<WalletTransactionVo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        List<WalletTransactionVo> walletTransactionVos = transactionMapper.selectList(wrapper);
        return walletTransactionVos;
    }

    @Override
    public IPage<WalletTransactionVo> selectTransactionByUserId(boolean isDeposit, String username,Integer pageSize,Integer current) {
        IPage<WalletTransactionVo> page = new Page<>(current,pageSize);
        QueryWrapper<WalletTransactionVo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        wrapper.eq("type",isDeposit?0:1);
        /// mybatis plus 的排序方式
        wrapper.orderByDesc(Arrays.asList(new String[] {"create_time"}));
        IPage<WalletTransactionVo> walletTransactionVoIPage = transactionMapper.selectPage(page, wrapper);
        return walletTransactionVoIPage;
    }
}
