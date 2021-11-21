package com.yicen.flutter_service_demo.db.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * qrtz_blob_triggers
 * @author 
 */
@Data
public class QrtzSimpleTriggers extends QrtzSimpleTriggersKey implements Serializable {
    private byte[] blobData;

    private static final long serialVersionUID = 1L;
}