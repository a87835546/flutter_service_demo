package com.yicen.flutter_service_demo.entity;

import lombok.Data;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Resource
public class UserDo implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private  String password;
}
