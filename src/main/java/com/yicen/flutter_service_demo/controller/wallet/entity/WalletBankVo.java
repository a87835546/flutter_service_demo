package com.yicen.flutter_service_demo.controller.wallet.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * wallet_bank
 * @author 
 */
@Data
@ApiOperation("银行卡模型")
@TableName("wallet_bank")
public class WalletBankVo implements Serializable {


    private Integer id;

    /**
     * 银行卡的名称
     */

    @TableField("name")
    private String bankName;

    private String province;

    private String city;

    private String address;

    /**
     * 卡号
     */
    private String number;

    private Date createTime;

    private String userName;

    private String bankIconUrl;

    private Integer userId;

    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}