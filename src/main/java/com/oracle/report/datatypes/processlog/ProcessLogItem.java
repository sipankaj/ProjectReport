package com.oracle.report.datatypes.processlog;

import com.oracle.report.services.implementation.ProcessLogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessLogItem {
    String key;
    String value;
    long timestamp;

    public ProcessLogItem(String key, String value) {
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }
}
