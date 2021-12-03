package com.yicen.flutter_service_demo.controller.message.serivce.impl;

import com.yicen.flutter_service_demo.controller.message.entity.Message;
import com.yicen.flutter_service_demo.controller.message.mapper.MessageMapper;
import com.yicen.flutter_service_demo.controller.message.serivce.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper mapper;

    @Override
    public List<Message> getMessages() {
        return mapper.queryAll();
    }

    @Override
    public int insert(Message message) {
        mapper.insert(message);
        return 0;
    }

    @Override
    public Message queryById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Message> queryByType(int id) {
        return mapper.queryAllByType(id);
    }
}
