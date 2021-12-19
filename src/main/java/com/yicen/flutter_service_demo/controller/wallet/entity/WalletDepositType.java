package com.yicen.flutter_service_demo.controller.wallet.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.annotation.Resource;

/**
 * wallet_deposit_type
 * @author 
 */
@Data
@TableName(value = "wallet_deposit_type")
@ApiModel("钱包的充值方式")
@Resource
public class WalletDepositType implements Serializable {
    private Integer id;

    private String name;

    private String logoUrl;

    /**
     * 支付类型
     */
    private Integer paymentType;

    private String showName;

    private Integer maxAmount;

    private Integer minAmount;

    /**
     * 钱包类型
     */
    private Integer amountType;

    private static final long serialVersionUID = 1L;
}