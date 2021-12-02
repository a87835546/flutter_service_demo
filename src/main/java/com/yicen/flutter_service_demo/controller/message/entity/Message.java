package com.yicen.flutter_service_demo.controller.message.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * message
 *
 * @author
 */
@ApiModel(value = "com.yicen.flutter_service_demo.controller.message.entity.Message消息")
@Data
public class Message implements Serializable {
    private Integer id;

    private Integer infoId;

    private Boolean isDelete;

    private String createUse;

    /**
     * 是否推送
     */
    @ApiModelProperty(value = "是否推送")
    private Boolean isPush;

    private Boolean isMessage;

    private Boolean isRead;

    /**
     * 0 -- 活动
     * 1 -- 通知
     * 2 -- 公告
     */
    @ApiModelProperty(value = "0 -- 活动 1 -- 通知 2 -- 公告")
    private Byte messageType;

    private String textContent;

    private String title;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}