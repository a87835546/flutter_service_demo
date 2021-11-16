package com.yicen.flutter_service_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Data
@Service
@TableName("bs_province")
@ApiModel("省  表")
public class Province implements Serializable {

    @TableId(value = "PROVINCE_ID",type = IdType.AUTO)
    private Integer id;

    @TableField("PROVINCE_NAME")
    private  String name;

    @TableField("PROVINCE_CODE")
    private String code;


    private List<City> cityList;
}
