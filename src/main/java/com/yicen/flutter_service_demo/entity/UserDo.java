package com.yicen.flutter_service_demo.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Resource
@ApiModel("用户注册的vo")
public class UserDo implements Serializable {
    @NotBlank
    @ApiModelProperty("注册的用户名")
    private String username;

    @NotBlank
    @ApiModelProperty("注册的密码")
    private  String password;

    @ApiModelProperty("用户的email")
    private String email;

    @ApiModelProperty("邀请码")
    private String inviteCode;

    @Override
    public String toString() {
        return "UserDo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
