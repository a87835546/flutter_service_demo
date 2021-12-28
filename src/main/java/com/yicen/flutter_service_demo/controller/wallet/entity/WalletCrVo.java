package com.yicen.flutter_service_demo.controller.wallet.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * wallet_cr
 * @author 
 */
@Data
@TableName("wallet_cr")
public class WalletCrVo implements Serializable {
    private Integer id;

    private Integer protocol;

    /**
     * 钱包地址
     */
    private String address;

    /**
     * 钱包的名称
     */
    private String name;

    private Date createTime;

    private Boolean isDelete;

    private String modifyUser;

//    @TableField(exist = false)
    @JsonIgnore
    private String userId;

    private static final long serialVersionUID = 1L;
}