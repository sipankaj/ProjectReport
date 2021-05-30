package com.oracle.report.services.implementation;

import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ErrorCodes;
import com.oracle.report.services.interfaces.IGenerateReportService;

import com.oracle.report.services.interfaces.IProcessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateReportService implements IGenerateReportService {
    Logger logger = LoggerFactory.getLogger(GenerateReportService.class);
    @Autowired
    IProcessLogService processLogService;

    @Override
    public void generateReport() throws SystemException {
        logger.debug("Enter - GenerateReportService:generateReport");
        try {
            processLogService.printReport();
        } catch (Exception e) {
            logger.error("Exception - {} ", e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            throw new SystemException(ErrorCodes.GENERATE_REPORT_PROCESS_FAILURE, errorMessage, e);
        }
        logger.debug("Exit - GenerateReportService:generateReport");
    }
}
