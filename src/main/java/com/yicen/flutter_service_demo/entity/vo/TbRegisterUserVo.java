package com.yicen.flutter_service_demo.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
@Resource
@ApiModel("tb_用户注册的vo")
public class TbRegisterUserVo implements Serializable {

    private String registerCode;

    private String username;

    private String name;
}
