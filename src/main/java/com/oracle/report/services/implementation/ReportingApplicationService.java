package com.oracle.report.services.implementation;

import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.services.interfaces.IComputeOperationsService;
import com.oracle.report.services.interfaces.IDataValidatorService;
import com.oracle.report.services.interfaces.IFileProcessorService;
import com.oracle.report.services.interfaces.IReportingApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *Author - Pankaj
 *Date - 30th May 2021
 *Description - ReportingApplicationService provides APIs to process the project data records from file and compute operations on data.
 */
@Component
public class ReportingApplicationService implements IReportingApplicationService {
    Logger logger = LoggerFactory.getLogger(ReportingApplicationService.class);
    @Autowired
    IFileProcessorService fileProcessorService;
    @Autowired
    IDataValidatorService dataValidatorService;
    @Autowired
    IComputeOperationsService computeOperationsService;

    /**
     * @throws SystemException
     */
    @Override
    public void initializeProcess() throws SystemException {
        logger.debug("Enter - ReportingApplicationService:initializeProcess");
        fileProcessorService.loadFile();
        dataValidatorService.validate();
        logger.debug("Exit - ReportingApplicationService:initializeProcess");
    }

    /**
     * @throws SystemException
     */
    @Override
    public void computeData() throws SystemException {
        logger.debug("Enter - ReportingApplicationService:computeData");
        computeOperationsService.performOpertions();
        logger.debug("Exit - ReportingApplicationService:computeData");
    }

}
