package com.oracle.report.errorhandling.implementation;

import com.oracle.report.errorhandling.exceptions.BaseException;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ICustomExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler implements ICustomExceptionHandler {
    Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @Override
    public void handleException(Exception ex) {
        if (ex instanceof SystemException) {
            logger.error("error code is {}", ((SystemException) ex).getErrorCode());
            logger.error("{}.Original Exception cause is:- {}", ex.getMessage(), ((SystemException) ex).getOriginalException().getMessage());
        } else if (ex instanceof BaseException) {
            ((BaseException) ex).getOriginalException().printStackTrace();
            logger.error("error code is {}", ((BaseException) ex).getErrorCode());
            logger.error("{}.Original Exception cause is:- {}", ex.getMessage(), ((BaseException) ex).getOriginalException().getMessage());
        } else {
            logger.error("Unknown error");
            logger.error(ex.getMessage());
        }
    }
}
