package com.yicen.flutter_service_demo.entity;


import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Resource
public class User implements Serializable {

    private  String username;

    private  String password;

    private  String id;

    private  String createdDate;
}
