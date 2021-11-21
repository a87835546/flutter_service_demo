package com.yicen.flutter_service_demo.db.vo;

import java.io.Serializable;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * qrtz_blob_triggers
 * @author 
 */
@Data
@Slf4j
@Component
@Api("QrtzBlobTriggers --- 测试")
public class QrtzBlobTriggers extends QrtzBlobTriggersKey implements Serializable {
    private byte[] blobData;

    private static final long serialVersionUID = 1L;
}