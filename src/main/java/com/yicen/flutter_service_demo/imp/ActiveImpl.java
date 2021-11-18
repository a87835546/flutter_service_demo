package com.yicen.flutter_service_demo.imp;

import com.yicen.flutter_service_demo.entity.Active;
import com.yicen.flutter_service_demo.mapper.ActiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveImpl {

    @Autowired
    private ActiveMapper mapper;

    public List<Active> queryAll(){
        return mapper.selectList(null);
    }
}
