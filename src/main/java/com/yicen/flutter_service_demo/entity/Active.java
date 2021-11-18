package com.yicen.flutter_service_demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Resource
@TableName("tb_active")
@ApiModel("活动表")
public class Active implements Serializable {

    @TableId
    @ApiModelProperty("活动id")
    private Integer id;

    @ApiModelProperty("活动标题")
    private String title;

    @ApiModelProperty("活动图片的url")
    private  String url;

    @ApiModelProperty("活动描述")
    private String description;
}
