package com.yicen.flutter_service_demo.db.dao;

import com.yicen.flutter_service_demo.db.vo.TbMeeting;
import com.yicen.flutter_service_demo.db.vo.TbMeetingKey;

public interface TbMeetingDao {
    int deleteByPrimaryKey(TbMeetingKey key);

    int insert(TbMeeting record);

    int insertSelective(TbMeeting record);

    TbMeeting selectByPrimaryKey(TbMeetingKey key);

    int updateByPrimaryKeySelective(TbMeeting record);

    int updateByPrimaryKeyWithBLOBs(TbMeeting record);
}