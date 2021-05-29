package com.oracle.report.services.interfaces;

import org.omg.CORBA.SystemException;

public interface IComputeProjectReportService {

    public void initializeProcess() throws SystemException;
    public void readData() throws SystemException;

}
