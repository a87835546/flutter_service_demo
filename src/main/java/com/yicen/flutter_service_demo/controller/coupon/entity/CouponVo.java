package com.yicen.flutter_service_demo.controller.coupon.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * coupon
 * @author 
 */
@Data
@TableName("coupon")
public class CouponVo implements Serializable {
    private Integer id;

    private String displayName;

    /**
     * 是否显示这个券
     */
    private Boolean isShow;

    private String picUrl;

    private String startDate;

    private String endDate;


    private String createTime;

    private Integer state;

    private static final long serialVersionUID = 1L;
}