package com.yicen.flutter_service_demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.beans.ConstructorProperties;
import java.io.Serializable;
@Data
public class Result<T>  implements Serializable {

    private boolean success;

    private  Integer code;

    private  String message;

    private T data;

    public static  Result ok(){
        Result result = new Result();
        result.setCode(HttpStatus.OK.value());
        result.setSuccess(true);
        result.setMessage("请求成功");
        result.setData(null);
        return  result;
    }

    public static  Result ok(Object data){

        Result result =  Result.ok();
        result.setData(data);
        System.out.println("result ok -->>>>>" + result);
        return  result;
    }

    public static Result ok(String msg){
        Result result =  Result.ok();
        result.setMessage(msg);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setSuccess(false);
        result.setData(null);
        return  result;
    }

    public static Result error(String msg){
        Result result = Result.error();
        result.setMessage(msg);
        return result;
    }

    public static Result error(Integer code){
        Result result = Result.error();
        result.setCode(code);
        return result;
    }

    public static Result error(Integer code,String msg){
        Result result = Result.error();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    @Data
    public static class  PageInfo{
        @ApiModelProperty("当前页")
        protected int currentPage;
        @ApiModelProperty("页大小")
        protected int pageSize;
        @ApiModelProperty("总记录数")
        protected long totalCount;
        @ApiModelProperty("总页数")
        protected long totalPage;

        public PageInfo() {
        }

        @ConstructorProperties({"currentPage", "pageSize", "totalCount", "totalPage"})
        public PageInfo(int currentPage, int pageSize, long totalCount, long totalPage) {
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.totalCount = totalCount;
            this.totalPage = totalPage;
        }
    }
}
