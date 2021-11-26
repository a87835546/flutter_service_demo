package com.yicen.flutter_service_demo.controller.active.serivce.impl.ActiveServiceImpl;

import com.yicen.flutter_service_demo.controller.active.entity.Active;
import com.yicen.flutter_service_demo.controller.active.mapper.ActiveMapper;
import com.yicen.flutter_service_demo.controller.active.serivce.ActiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    private ActiveMapper mapper;

    @Override
    public List<Active> queryAll() {
        return mapper.queryAll();
    }
}
