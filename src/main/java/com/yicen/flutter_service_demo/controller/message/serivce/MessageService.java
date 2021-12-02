package com.yicen.flutter_service_demo.controller.message.serivce;

import com.yicen.flutter_service_demo.controller.message.entity.Message;

import java.util.List;

public interface MessageService {

    public List<Message> getMessages();

    public  int insert(Message message);

    public  Message queryById(int id);
}
