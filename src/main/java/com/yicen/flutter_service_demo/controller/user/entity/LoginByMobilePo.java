package com.yicen.flutter_service_demo.controller.user.entity;

import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Resource
public class LoginByMobilePo implements Serializable {

    private String mobile;

    private String code;

    @Override
    public String toString() {
        return "LoginByMobilePo{" +
                "mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
