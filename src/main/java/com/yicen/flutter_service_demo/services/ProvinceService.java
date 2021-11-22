package com.yicen.flutter_service_demo.services;

import com.yicen.flutter_service_demo.entity.Province;

import java.util.List;

public interface ProvinceService {

    public List<Province> queryAll();

    public List<Province> test();
}
