package com.yicen.flutter_service_demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

@Data
@Resource
@TableName("user")
public class User implements Serializable {


    private String username;

    private String password;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("create_date")
    private Date createTime;

    @TableField("update_date")
    private Date updateTime;

    private String email;

    private String male;

    private String address;


}
