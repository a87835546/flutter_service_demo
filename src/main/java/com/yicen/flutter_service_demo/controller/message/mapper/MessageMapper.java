package com.yicen.flutter_service_demo.controller.message.mapper;

import com.yicen.flutter_service_demo.controller.message.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    @Select("select * from message")
    List<Message> queryAll();

    @Select("select * from message where message_type = #{type}")
    List<Message> queryAllByType(Integer type);
}