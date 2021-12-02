package com.yicen.flutter_service_demo.controller.message.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class InsertMessageVo implements Serializable {
    /**
     * 0 -- 活动
     * 1 -- 通知
     * 2 -- 公告
     */
    @ApiModelProperty(value = "0 -- 活动 1 -- 通知 2 -- 公告")
    private Byte messageType;

    private String textContent;

    private String title;

    private Integer infoId;

    private String createUse;
}
