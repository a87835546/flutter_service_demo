package com.yicen.flutter_service_demo.exception;

import lombok.Data;

@Data
public class DemoException extends RuntimeException{

    private String msg;
    private Integer code = 500;

    public DemoException(String msg){
        super(msg);
        this.msg = msg;
    }

    public DemoException(String msg,Integer code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
