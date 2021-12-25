package com.yicen.flutter_service_demo.controller.wallet.service.Impl;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletDepositTypeMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletDepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WalletDepositServiceImpl implements WalletDepositService {

    @Autowired
    private WalletDepositTypeMapper walletDepositTypeMapper;

    @Autowired
    private WalletDepositChannelServiceImpl channelService;

    @Override
    public List<WalletDepositTypeVo> getDepositStyle() {
        List<WalletDepositTypeVo> walletDepositTypeVos = walletDepositTypeMapper.selectAll();
        List<WalletDepositChannelVo> walletDepositChannelVos = walletDepositTypeMapper.selectAll2();
        for (WalletDepositTypeVo e:
                walletDepositTypeVos) {
            List<WalletDepositChannelVo> temp = new ArrayList<>();
            for (WalletDepositChannelVo v:
                    walletDepositChannelVos) {
                if (e.getId().equals(v.getDepositTypeId())){
                    temp.add(v);
                }
            }
            e.setChannelVoList(temp);
        }
        return walletDepositTypeVos;
    }

    @Override
    public WalletDepositTypeVo test() {
        return walletDepositTypeMapper.test();
    }

    @Override
    public List<WalletDepositChannelVo> getChannel() {
        return walletDepositTypeMapper.selectAll3();
    }
}
