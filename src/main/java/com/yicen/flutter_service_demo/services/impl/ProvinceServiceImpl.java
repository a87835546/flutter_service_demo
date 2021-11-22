package com.yicen.flutter_service_demo.services.impl;

import com.yicen.flutter_service_demo.entity.City;
import com.yicen.flutter_service_demo.entity.Province;
import com.yicen.flutter_service_demo.mapper.CityMapper;
import com.yicen.flutter_service_demo.mapper.ProvinceMapper;
import com.yicen.flutter_service_demo.services.CityService;
import com.yicen.flutter_service_demo.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService, CityService {
    @Autowired
    private ProvinceMapper mapper;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<Province> queryAll(){
        return mapper.selectAll();
    }

    @Override
    public List<Province> test(){
        return  mapper.selectList(null);
    }

    @Override
    public List<City> selectCity(){
        return cityMapper.getAll();
    }
}
