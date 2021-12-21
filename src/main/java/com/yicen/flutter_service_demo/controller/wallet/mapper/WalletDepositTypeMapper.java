package com.yicen.flutter_service_demo.controller.wallet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositChannelVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WalletDepositTypeMapper extends BaseMapper<WalletDepositTypeVo> {

    @Select("select * from wallet_deposit_type")
    List<WalletDepositTypeVo> selectAll();

    @Select("select * from wallet_deposit_type type left join wallet_deposit_channel channel on type.id = channel.deposit_type_id")
    List<WalletDepositTypeVo> selectAll1();

    @Select("select * from wallet_deposit_channel")
    List<WalletDepositChannelVo> selectAll2();

    @Select("select * from wallet_deposit_type where id = 1")
    WalletDepositTypeVo test();

}