package com.yicen.flutter_service_demo.shiro;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${flutter.jwt.secret}")
    private String secret;

    @Value("${flutter.jwt.expire}")
    private Integer expire;

    public String createToken(String userId){
        Date date = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR,5);
        return "";
    }
}
