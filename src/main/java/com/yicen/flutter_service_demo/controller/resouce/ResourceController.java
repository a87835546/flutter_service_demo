package com.yicen.flutter_service_demo.controller.resouce;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.entity.ResourceBean;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.utils.FastDfsCommon;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("upload")
@Slf4j
@ApiOperation("上传文件相关的接口")
public class ResourceController {

    @RequestMapping(value={"/images"}, method= RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String extName = "";
        String fileName = "";
        String originalFilename = file.getOriginalFilename();
        log.info("original name {}",originalFilename);
        if(originalFilename.contains(".")) {
            //拆分文件路径
            String[] fileArray = originalFilename.split("\\.");
            //获取文件扩展名

            extName = fileArray[fileArray.length-1];
            //获取文件名
            fileName = fileArray[0];
            log.info("file array ----->>>>> {} ext name --->>> {}",fileArray,extName);
        }else {
            fileName = originalFilename;
        }
        byte[] bytes = null;
        try {
            bytes = file.getBytes(); //将文件转换成字节流形式
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用上传文件的具体方法
        String fileId= FastDfsCommon.upload(bytes,extName);
        ResourceBean resourceBean = new ResourceBean(fileId, fileName);
        log.info("file id :" + fileId + "file name {}",fileName);
        log.info("ext name {}",extName);
        return Result.ok(resourceBean);
    }

}
