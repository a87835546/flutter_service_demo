package com.yicen.flutter_service_demo.controller.wallet.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InsertBankVo implements Serializable {

    /**
     * 银行卡的名称
     */
    private String bankName;

    private String province;

    private String city;

    private String address;

    /**
     * 卡号
     */
    private String number;
}
