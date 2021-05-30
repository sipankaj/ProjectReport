package com.oracle.report.services.interfaces;

import com.oracle.report.errorhandling.exceptions.SystemException;

public interface IDataValidatorService {
    public void validate() throws SystemException;
}
