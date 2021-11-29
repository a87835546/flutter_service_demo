package com.yicen.flutter_service_demo.controller.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yicen.flutter_service_demo.controller.message.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {


}
