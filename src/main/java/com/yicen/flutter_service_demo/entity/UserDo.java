package com.yicen.flutter_service_demo.entity;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Resource
public class UserDo implements Serializable {
    private String username;
    private  String password;
}
