package com.oracle.report.services.interfaces;

import com.oracle.report.datatypes.enums.DataOperation;
import com.oracle.report.datatypes.processlog.ProcessLogItem;

import java.util.List;
import java.util.Map;

public interface IProcessLogService {
    public void logData(DataOperation operation, ProcessLogItem item);

    public void printReport();

}
