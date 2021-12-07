package com.yicen.flutter_service_demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Resource
@TableName("user")
public class User implements Serializable {

    private String username;

    private String password;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("create_date")
    private String createTime;

    @TableField("update_date")
    private String updateTime;

    private String email;

    private Boolean gender;

    private String address;

    private String balance;

    @TableField("nick_name")
    private String nickName;

    private String mobile;

    private String birthday;

    private String avatar;

    private String realName;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", balance='" + balance + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatar='" + avatar + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
