package com.oracle.report.services.implementation;

import com.oracle.report.services.interfaces.IComputeProjectReportService;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputeProjectReportService implements IComputeProjectReportService {

    @Autowired
    FileProcessorService fileProcessorService;
    @Override
    public void initializeProcess() throws SystemException {
        fileProcessorService.loadFile();
    }

    @Override
    public void readData() throws SystemException {

    }
}
