package com.yicen.flutter_service_demo.controller.wallet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WalletTransactionMapper extends BaseMapper<WalletTransactionVo> {
    @Select("select * from wallet_transaction")
    IPage<WalletTransactionVo> selectTransactionByUserId(Page<WalletTransactionVo> page);
}