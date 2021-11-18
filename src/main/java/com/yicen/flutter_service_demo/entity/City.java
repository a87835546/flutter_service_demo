package com.yicen.flutter_service_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Slf4j
@Resource
@TableName("bs_city")
@ApiModel("城市表")
public class City implements Serializable {

    @TableId(value = "CITY_ID",type = IdType.AUTO)
    @ApiModelProperty("城市id")
    private Integer id;

    @TableField("CITY_NAME")
    @ApiModelProperty("城市名")
    private String name;

    @TableField("CITY_CODE")
    @ApiModelProperty("城市的编码")
    private String code;

    @TableField("SHORT_NAME")
    @ApiModelProperty("城市的短名称")
    private String shortName;

    @TableField("PROVINCE_CODE")
    @ApiModelProperty("省的编码")
    private String provinceCode;

    private String provinceName;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortName='" + shortName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
