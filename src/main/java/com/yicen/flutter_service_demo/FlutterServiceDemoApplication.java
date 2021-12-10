package com.yicen.flutter_service_demo;

import com.yicen.flutter_service_demo.utils.FastDfsCommon;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.yicen.flutter_service_demo.**")
@SpringBootApplication
public class FlutterServiceDemoApplication {

    public static void main(String[] args) {
        String filePath = "fastdfs_client.conf";
        if (args.length > 0) {
            filePath = args[0];
        }
        FastDfsCommon.setFile(filePath);
        SpringApplication.run(FlutterServiceDemoApplication.class, args);
    }

}
