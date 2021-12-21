package com.yicen.flutter_service_demo.controller.wallet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wallet_deposit_channel")
public class WalletDepositChannelVo {
    private Integer id;

    @TableField("此支付方式的类型")
    private Integer depositTypeId;

    private String name;

    private String logoUrl;

    /**
     * 支付类型
     */
    private Integer paymentType;

    private String showName;

    private String fixedAmount;

    private Integer maxAmount;

    private Integer minAmount;

    private static final long serialVersionUID = 1L;

}
