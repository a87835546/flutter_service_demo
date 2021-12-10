package com.yicen.flutter_service_demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author binghe
 * @version 1.0.0
 * @description 上传图片后的返回数据
 */
@Data
public class ResourceBean implements Serializable {
    private static final long serialVersionUID = -2788538880352897307L;

    /**
     * 文件的访问路径
     */
    private String fileUrl;

    /**
     * 文件名称
     */
    private String fileName;

    public ResourceBean() {
        super();
    }

    public ResourceBean(String fileUrl, String fileName) {
        super();
        this.fileUrl = getResourcesUrl(fileUrl);
        this.fileName = fileName;
    }

    private static final String BASE_RESOURCES_URL = "http://192.168.55.131:8881/";
    public static String getResourcesUrl(String fileId) {
        return BASE_RESOURCES_URL.concat(fileId);
    }
}