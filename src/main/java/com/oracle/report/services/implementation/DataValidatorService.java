package com.oracle.report.services.implementation;

import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ErrorCodes;
import com.oracle.report.services.interfaces.IDataValidatorService;
import com.oracle.report.services.interfaces.IFileProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *Author - Pankaj
 *Date - 30th May 2021
 *Description - DataValidatorService provides APIs to validate the input data.
 */
@Component
public class DataValidatorService implements IDataValidatorService {
    Logger logger = LoggerFactory.getLogger(DataValidatorService.class);
    @Autowired
    IFileProcessorService fileProcessorService;

    /**
     * @throws SystemException
     */
    @Override
    public void validate() throws SystemException {
        logger.debug("Enter - DataValidatorService:validate");
        if(!fileProcessorService.getData().isPresent())
            throw new SystemException(ErrorCodes.VALIDATE_REPORT_PROCESS_FAILURE, "Data is not present",new Exception());
        logger.debug("Exit - DataValidatorService:validate");
    }
}
