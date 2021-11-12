package com.yicen.flutter_service_demo.entity;


import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

@Data
@Resource
public class User implements Serializable {

    private  String username;

    private  String password;

    private  String id;

    private  String createdDate;

    private Date createTime;

    private  Date updateTime;

    private String email;

    private String male;
}
