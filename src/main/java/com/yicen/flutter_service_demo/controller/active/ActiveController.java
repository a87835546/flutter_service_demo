package com.yicen.flutter_service_demo.controller.active;


import com.yicen.flutter_service_demo.config.NoNeedLogin;
import com.yicen.flutter_service_demo.controller.active.entity.Active;
import com.yicen.flutter_service_demo.controller.active.serivce.impl.ActiveServiceImpl.ActiveServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/activity/")
@Slf4j
@Api("活动数据对应的接口")
public class ActiveController {

    final static String kBannerRedis = "BANNER_REDIS_NAME";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActiveServiceImpl activeService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("queryAll")
    public Result<List<Active>> getActiveList() {
        List<Active> actives = null;
        try {
            actives = activeService.queryAll();
        }catch (Exception e){
            log.warn(e.getLocalizedMessage());
        } finally {
            return Result.ok(actives);
        }
    }

    @GetMapping("banner")
    @ApiOperation("获取首页的banner数据")
    @NoNeedLogin
    public Result<List> getBannerList(){
        String banner = null;
        List<Active> actives = null;
        try{
            banner = redisUtil.get(kBannerRedis);
        }catch (Exception error){
            log.warn(error.getMessage());
        }
        if (StringUtil.isNullOrEmpty(banner)){
            actives = activeService.queryAll();
            redisUtil.set(kBannerRedis, String.valueOf(JSONArray.fromObject(actives)));
        }else {
            JSONArray jsonArray = JSONArray.fromObject(redisUtil.get(kBannerRedis));
            actives = (List<Active>)JSONArray.toCollection(jsonArray, Active.class);
        }

        return Result.ok(actives);
    }

    public void test(){
        log.info(activeService.queryAll().toString());
    }


    public void test2(){

        redisTemplate.opsForValue().set("name","zhangsan");
        log.info("result :" + redisTemplate.opsForValue().get("name"));
    }

    @GetMapping("test")
    @ApiOperation("测试方法。。。。。。")
    public Result test3(){
        return Result.ok(new Date().toString());
    }

    @GetMapping("signInfo")
    public Result getAccountSignInfo(){
        return Result.ok();
    }

    @GetMapping("vipInfo")
    public Result getVipInfo(){
        return Result.ok();
    }
}
