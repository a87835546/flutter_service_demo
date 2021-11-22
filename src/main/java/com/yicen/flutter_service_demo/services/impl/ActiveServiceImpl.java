package com.yicen.flutter_service_demo.services.impl;

import com.yicen.flutter_service_demo.entity.Active;
import com.yicen.flutter_service_demo.mapper.ActiveMapper;
import com.yicen.flutter_service_demo.services.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActiveMapper mapper;

    @Override
    public List<Active> queryAll(){
        return mapper.selectList(null);
    }
}
