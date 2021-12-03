package com.yicen.flutter_service_demo.controller.message;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.message.entity.InsertMessageVo;
import com.yicen.flutter_service_demo.controller.message.entity.Message;
import com.yicen.flutter_service_demo.controller.message.serivce.impl.MessageServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("message")
@ApiOperation("消息相关的接口")
@Slf4j
public class MessageController {

    private static final String kRedisPrefix = "message:";
    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("unreadCount")
    @ApiOperation("获取未读的消息数量")
    @NeedLogin
    public Result getUnreadCount() {
        List<Message> messages = messageService.getMessages();
        int count = (int) messages.stream().filter(e -> !e.getIsRead()).count();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("count", count);
        return Result.ok(objectObjectHashMap);
    }

    @GetMapping("read")
    @NeedLogin
    @ApiOperation("读取某条信息")
    public Result readMessage(HttpServletRequest request,@RequestParam Integer messageId) {
        String key = redisUtil.get(kRedisPrefix + messageId + "");
        User user = JwtUtil.getUser(request.getHeader("token"));
        redisUtil.set(kRedisPrefix + messageId + "", user.getId() + (StringUtil.isNullOrEmpty(key) ? key : ""));
        return Result.ok();
    }

    @GetMapping("info")
    @NeedLogin
    @ApiOperation("获取消息列表")
    public Result getInfo(HttpServletRequest request,@ApiParam("消息类型") @RequestParam Integer messageType) {
        /**
         * 0 -- 活动
         * 1 -- 通知
         * 2 -- 公告
         */
        List<Message> messages = messageService.queryByType(messageType);

        if (messageType == 0) {
            Map<String ,Object> map = new HashMap<>();

            AtomicInteger count = new AtomicInteger();
            User user = JwtUtil.getUser(request.getHeader("token"));
            messages.forEach(e -> {
                String s = redisUtil.get(kRedisPrefix + e.getId());
                if (s.contains(user.getId().toString())) {
                    e.setIsRead(true);
                } else {
                    e.setIsRead(false);
                   count.getAndIncrement();
                }
            });
        }
        return Result.ok(messages);
    }


    @NeedLogin
    @ApiOperation("添加消息、活动。通知")
    @PostMapping("add")
    public Result insertMessage(@RequestBody InsertMessageVo messageVo) {
        Message message = new Message();
        BeanUtils.copyProperties(message, messageVo);
        int i = messageService.insert(message);
        writeIdToRedis(message.getId());
        return Result.ok(i == 0 ? "添加活动失败" : "添加活动成功");
    }

    ///把消息写入redis
    public void writeIdToRedis() {
        List<Message> messages = messageService.getMessages();
        messages.forEach(e -> {
            if (e.getMessageType() == 0) {
                String s = redisUtil.get(kRedisPrefix + e.getId() + "");
                if (StringUtil.isNullOrEmpty(s)) {
                    redisUtil.set(kRedisPrefix + e.getId() + "", "");
                }
            }
        });
    }

    ///把消息写入redis
    public void writeIdToRedis(int id) {
        Message message = messageService.queryById(id);
        if (message.getMessageType() == 0) {
            String s = redisUtil.get(kRedisPrefix + message.getId() + "");
            if (s.isEmpty()) {
                redisUtil.set(kRedisPrefix + message.getId() + "", "");
            }
        }
    }

}
