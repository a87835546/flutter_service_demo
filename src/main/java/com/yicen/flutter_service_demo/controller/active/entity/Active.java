package com.yicen.flutter_service_demo.controller.active.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

@Resource
@TableName("active")
@ApiModel
@Data
public class Active implements Serializable {
    @TableId
    private Integer id;

    @ApiModelProperty("活动的标题")
    private String title;

    @ApiModelProperty("活动的连接")
    private String url;

    @ApiModelProperty("活动图片的连接")
    private String picUrl;

    @ApiModelProperty("活动的描述")
    private String desc;

    @Override
    public String toString() {
        return "Active{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
