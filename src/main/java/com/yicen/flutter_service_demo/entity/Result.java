package com.yicen.flutter_service_demo.entity;

import lombok.Data;

@Data
public class Result<T> {

    private boolean success;

    private  Integer code;

    private  String message;

    private T data;

    public static  Result ok(){
        Result result = new Result();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("请求成功");
        result.setData(null);
        return  result;
    }

    public static  Result ok(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("请求成功");
        result.setData(data);
        System.out.println("result ok -->>>>>" + result);
        return  result;
    }
}
