package com.oracle.report.services.interfaces;

import com.oracle.report.errorhandling.exceptions.SystemException;

public interface IGenerateReportService {
    public void generateReport() throws SystemException;
}
