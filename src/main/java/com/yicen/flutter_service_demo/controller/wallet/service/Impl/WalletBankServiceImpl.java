package com.yicen.flutter_service_demo.controller.wallet.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletBankVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletBankMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WalletBankServiceImpl implements WalletBankService {

    @Autowired
    private WalletBankMapper walletBankMapper;


    @Override
    public List<WalletBankVo> getBankList(String userId) {
        QueryWrapper wrapper = new QueryWrapper<WalletBankVo>();
        wrapper.eq("user_id",userId);
        wrapper.eq("is_delete",false);
        return walletBankMapper.selectList(wrapper);
    }

    @Override
    public int insertBank(WalletBankVo bank) {
        return walletBankMapper.insert(bank);
    }

    @Override
    public int deleteBank(Integer id) {
        QueryWrapper wrapper = new QueryWrapper<WalletBankVo>();
        wrapper.eq("id",id);
        WalletBankVo vo = new WalletBankVo();
        vo.setIsDelete(true);
        return walletBankMapper.update(vo,wrapper);
    }

    @Override
    public WalletBankVo queryByCardNumber(String number) {

        QueryWrapper wrapper = new QueryWrapper<WalletBankVo>();
        wrapper.eq("number",number);
        WalletBankVo bankVo = walletBankMapper.selectOne(wrapper);
        return bankVo;
    }

    @Override
    public int updateDelete(String cardNum) {
        QueryWrapper wrapper = new QueryWrapper<WalletBankVo>();
        wrapper.eq("number",cardNum);
        WalletBankVo vo = new WalletBankVo();
        vo.setIsDelete(true);
        return walletBankMapper.update(vo,wrapper);
    }
}
