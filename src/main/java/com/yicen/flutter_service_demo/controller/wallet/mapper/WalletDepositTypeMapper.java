package com.yicen.flutter_service_demo.controller.wallet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WalletDepositTypeMapper extends BaseMapper<WalletDepositType> {

    @Select("select * from wallet_deposit_type")
    List<WalletDepositType> selectAll();

    @Select("select * from wallet_deposit_type where id = 1")
    WalletDepositType test();

}