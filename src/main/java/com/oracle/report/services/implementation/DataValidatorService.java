package com.oracle.report.services.implementation;

import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.services.interfaces.IDataValidatorService;
import com.oracle.report.services.interfaces.IFileProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataValidatorService implements IDataValidatorService {
    Logger logger = LoggerFactory.getLogger(DataValidatorService.class);
    @Autowired
    IFileProcessorService fileProcessorService;

    @Override
    public void validate() throws SystemException {
        logger.debug("Enter - DataValidatorService:validate");
        // fileProcessorService.projectData
        logger.debug("Exit - DataValidatorService:validate");
    }
}
