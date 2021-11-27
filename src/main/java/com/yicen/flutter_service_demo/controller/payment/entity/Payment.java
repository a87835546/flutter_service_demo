package com.yicen.flutter_service_demo.controller.payment.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("payment")
public class Payment {

    @TableId
    @ApiModelProperty
    private Integer id;

    @ApiModelProperty("余额")
    private BigDecimal balance;

    @ApiModelProperty("用户id")
    private  Integer userId;

    @ApiModelProperty("最后更新时间")
    @TableField(value = "update_time")
    private Date time;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", balance=" + balance +
                ", userId=" + userId +
                ", time=" + time +
                '}';
    }
}
