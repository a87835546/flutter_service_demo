package com.yicen.flutter_service_demo.db.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * qrtz_blob_triggers
 * @author 
 */
@Data
public class TbDeptKey implements Serializable {
    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private static final long serialVersionUID = 1L;
}