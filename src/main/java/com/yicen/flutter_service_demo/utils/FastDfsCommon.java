package com.yicen.flutter_service_demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class FastDfsCommon {


    private static TrackerClient trackerClient;

    public static void setFile(String filePath) {
        try {
            log.info("初始化分布式文件系统服务开始...");
            if(filePath == null || filePath.trim().isEmpty()) {
                filePath = "fastdfs_client.conf";
            }
            ClientGlobal.init(filePath);
            TrackerGroup trackerGroup = ClientGlobal.g_tracker_group;
            trackerClient = new TrackerClient(trackerGroup);
            log.info("初始化分布式文件系统服务完成...");
        } catch (Exception e) {
            log.error("加载文件异常：{}",e.getLocalizedMessage() );
        }
    }

    /**
     * @param data 数据
     * @param extName 文件扩展名
     * @return 上传成功返回id，失败返回null
     */
    public static String upload(byte[] data, String extName) {
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = null;
        try {
            NameValuePair[] meta_list = null; // new NameValuePair[0];

            trackerServer = trackerClient.getTrackerServer();
            if (trackerServer == null) {
                log.error("getConnection return null");
            }
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            String fileid = storageClient1.upload_file1(data, extName, meta_list);
            return fileid;
        } catch (Exception ex) {
            log.error("上传文件异常：{}", ex);
            return null;
        }finally{
            try {
                storageClient1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            storageClient1 = null;
        }
    }

    /**
     * <B>方法名称：</B>下载方法<BR>
     * <B>概要说明：</B>通过文件id进行下载<BR>
     * @param fileId 文件id
     * @return 返回InputStream
     */
    public static InputStream download(String groupName, String fileId) {
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            if (trackerServer == null) {
                log.error("getConnection return null");
            }
            storageServer = trackerClient.getStoreStorage(trackerServer, groupName);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            byte[] bytes = storageClient1.download_file1(fileId);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (Exception ex) {
            log.error("下载文件异常：{}", ex);
            return null;
        } finally {
            try {
                storageClient1.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            storageClient1 = null;
        }
    }
}
