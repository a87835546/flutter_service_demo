package com.yicen.flutter_service_demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Resource
@TableName("tb_active")
public class Active implements Serializable {

    @TableId
    private Integer id;

    private String title;

    private  String url;


    private String description;
}
