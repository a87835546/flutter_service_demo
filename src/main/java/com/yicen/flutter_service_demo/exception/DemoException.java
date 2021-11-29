package com.yicen.flutter_service_demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DemoException extends RuntimeException{

    private String msg;
    private Integer code = 500;

    public DemoException(String msg){
        super(msg);
        this.msg = msg;
    }

    /// 服务 异常的
    public DemoException(){
        this.msg = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        this.code =  HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    ///自定义异常
    public DemoException(String msg,Integer code){
        super(msg);
        this.msg = msg;
        this.code =  code;
    }

}
