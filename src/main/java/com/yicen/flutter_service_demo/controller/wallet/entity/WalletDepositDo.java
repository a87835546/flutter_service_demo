package com.yicen.flutter_service_demo.controller.wallet.entity;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

@ApiOperation("充值需要的参数")
@Data
public class WalletDepositDo implements Serializable {

    private Integer typeId;

    private Integer channelId;

    private Integer amountType;

    private Integer money;
}
