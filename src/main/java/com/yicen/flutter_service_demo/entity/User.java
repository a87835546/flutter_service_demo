package com.yicen.flutter_service_demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Resource
@TableName("user")
public class User implements Serializable {

    private String username;

    @JsonIgnore
    @TableField(exist = false)
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
