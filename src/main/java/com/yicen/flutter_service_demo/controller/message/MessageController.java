package com.yicen.flutter_service_demo.controller.message;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.message.serivce.impl.MessageServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("message")
@ApiOperation("消息相关的接口")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("unreadCount")
    @ApiOperation("获取未读的消息数量")
    @NeedLogin
    public Result getUnreadCount(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("count",10);
        return Result.ok(objectObjectHashMap);
    }
}
