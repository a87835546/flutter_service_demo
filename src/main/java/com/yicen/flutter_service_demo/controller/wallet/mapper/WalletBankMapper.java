package com.yicen.flutter_service_demo.controller.wallet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletBankVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletBankMapper extends BaseMapper<WalletBankVo> {
}