package com.yicen.flutter_service_demo.config;


import com.yicen.flutter_service_demo.exception.DemoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map myErrorHandler(DemoException exception){
        Map map = new HashMap();
        map.put("code",exception.getCode());
        map.put("message",exception.getMsg());
        return map;
    }

//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView handleException(DemoException exception){
//        ModelAndView modelAndView = new ModelAndView();
//        Map map = new HashMap();
//        map.put("code",exception.getCode());
//        map.put("message",exception.getMsg());
//        modelAndView.addAllObjects(map);
//        return modelAndView;
//    }
}
