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

@Component
public class ReportingApplicationService implements IReportingApplicationService {
    Logger logger = LoggerFactory.getLogger(ReportingApplicationService.class);
    @Autowired
    IFileProcessorService fileProcessorService;
    @Autowired
    IDataValidatorService dataValidatorService;
    @Autowired
    IComputeOperationsService computeOperationsService;

    @Override
    public void initializeProcess() throws SystemException {
        logger.debug("Enter - ReportingApplicationService:initializeProcess");
        fileProcessorService.loadFile();
        dataValidatorService.validate();
        logger.debug("Exit - ReportingApplicationService:initializeProcess");
    }

    @Override
    public void computeData() throws SystemException {
        logger.debug("Enter - ReportingApplicationService:computeData");
        computeOperationsService.getUniqCustomerContractRelData();
        computeOperationsService.getUniqCustomerGeoZoneRelData();
        computeOperationsService.getBuildDurationGeoZoneRelData();
        computeOperationsService.getListOfCustomerGeoZoneRelData();
        logger.debug("Exit - ReportingApplicationService:computeData");
    }

}
