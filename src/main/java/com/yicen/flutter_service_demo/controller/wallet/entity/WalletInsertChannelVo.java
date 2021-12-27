package com.yicen.flutter_service_demo.controller.wallet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api("插入充值渠道的数据")
public class WalletInsertChannelVo implements Serializable {
    @ApiModelProperty("此支付方式的类型")
    private Integer depositTypeId;

    private String name;

    private String logoUrl;

    private String showName;
    private Integer maxAmount;

    private Integer minAmount;

    private static final long serialVersionUID = 1L;

}
