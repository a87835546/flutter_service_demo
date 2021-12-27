package com.yicen.flutter_service_demo.controller.wallet.service.Impl;

import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletInsertChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletDepositChannelMapper;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletDepositTypeMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletDepositChannelService;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletDepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WalletDepositChannelServiceImpl implements WalletDepositChannelService {

    @Autowired
    private WalletDepositChannelMapper channelMapper;


    @Override
    public List<WalletDepositChannelVo> getDepositStyle() {
        return null;
    }

    @Override
    public WalletDepositChannelVo test() {
        return null;
    }

    @Override
    public int insert(WalletDepositChannelVo vo) {
        WalletDepositChannelVo entity = new WalletDepositChannelVo();
        BeanUtils.copyProperties(vo,entity);

        int insert = channelMapper.insert(vo);
        return insert;
    }
}
