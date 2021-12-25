package com.yicen.flutter_service_demo.controller.wallet.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * wallet_transaction
 * @author 
 */
@Data
@TableName(value = "wallet_transaction")
public class WalletTransactionVo implements Serializable {
    private Integer id;

    /**
     * 金额
     */
    private Long amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 操作人员
     */
    private String userName;

    /**
     * 操作状态 -- 0 fail 1 success
     */
    private Boolean resultType;

    /**
     *  操作描述，例如 卡  转  卡
     */
    private String description;

    /**
     * 交易类型 0 存款 1 取款 
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}