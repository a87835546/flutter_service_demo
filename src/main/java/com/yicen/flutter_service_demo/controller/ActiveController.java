package com.yicen.flutter_service_demo.controller;

import com.yicen.flutter_service_demo.entity.Active;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.services.impl.ActiveServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/active/")
@Slf4j
public class ActiveController {

    @Autowired
    private ActiveServiceImpl activeService;

    @GetMapping("queryAll")
    Result<List<Active>> getActiveList() {
        List<Active> actives = null;
        try {
            actives = activeService.queryAll();
        }catch (Exception e){
            log.warn(e.getLocalizedMessage());
        } finally {
            return Result.ok(actives);
        }
    }
}
