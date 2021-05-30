package com.oracle.report.services.interfaces;

import com.oracle.report.errorhandling.exceptions.SystemException;

public interface IReportingApplicationService {

    public void initializeProcess() throws SystemException;

    public void computeData() throws SystemException;
}
