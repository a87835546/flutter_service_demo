package com.yicen.flutter_service_demo.services;

import com.yicen.flutter_service_demo.entity.TbUser;
import com.yicen.flutter_service_demo.entity.vo.TbRegisterUserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

@Component("UserDaoService")
public interface UserDaoService {
    @ApiOperation("tb_user 注册用户")
    public TbUser registerUser(TbRegisterUserVo vo);
}
