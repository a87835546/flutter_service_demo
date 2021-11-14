package com.yicen.flutter_service_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("GET","POST").allowedMethods().maxAge(60*60).allowCredentials(true);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
