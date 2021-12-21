package com.yicen.flutter_service_demo.controller.wallet.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.annotation.Resource;

/**
 * wallet_deposit_type
 * @author 
 */
@Data
@TableName(value = "wallet_deposit_type",autoResultMap = true)
@ApiModel("钱包的充值方式")
@Resource
@Accessors(chain = true)
public class WalletDepositTypeVo implements Serializable{
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

    @TableField(exist = false)
    private List<WalletDepositChannelVo> channelVoList;

    private static final long serialVersionUID = 1L;
}