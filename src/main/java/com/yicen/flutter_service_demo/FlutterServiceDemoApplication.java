package com.yicen.flutter_service_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.yicen.flutter_service_demo.mapper.**")
@SpringBootApplication
public class FlutterServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlutterServiceDemoApplication.class, args);
    }

}
