package com.yicen.flutter_service_demo.controller.vip;

import com.yicen.flutter_service_demo.entity.City;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.services.impl.ProvinceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    private ProvinceServiceImpl provinceService;

    @GetMapping("queryAll")
    public Result<Map<String, List>> getAll(){
        return Result.ok(provinceService.queryAll());
    }

    @GetMapping("test")
    public void test(){
        provinceService.test();
    }

    @GetMapping("test1")
    public List<City> test1(){
       return provinceService.selectCity();
    }

    @GetMapping("queryCites")
    public Result<List<City>> getCites(){
        return Result.ok(provinceService.selectCity());
    }
}
