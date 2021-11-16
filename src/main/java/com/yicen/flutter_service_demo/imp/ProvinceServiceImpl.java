package com.yicen.flutter_service_demo.imp;

import com.yicen.flutter_service_demo.entity.City;
import com.yicen.flutter_service_demo.entity.Province;
import com.yicen.flutter_service_demo.mapper.CityMapper;
import com.yicen.flutter_service_demo.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl {
    @Autowired
    private ProvinceMapper mapper;

    @Autowired
    private CityMapper cityMapper;

    public List<Province> queryAll(){
        return mapper.selectAll();
    }

    public List<Province> test(){
        return  mapper.selectList(null);
    }

    public List<City> selectCity(){
        return cityMapper.getAll();
    }
}
