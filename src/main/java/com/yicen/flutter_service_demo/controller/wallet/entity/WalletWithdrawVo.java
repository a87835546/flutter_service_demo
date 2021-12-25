package com.yicen.flutter_service_demo.controller.wallet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("取款需要的数据")
public class WalletWithdrawVo implements Serializable {


    private String bankId;

    private String cardNum;

    private Integer amount;

}
