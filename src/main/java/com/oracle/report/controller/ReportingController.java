package com.oracle.report.controller;


import com.oracle.report.errorhandling.interfaces.ICustomExceptionHandler;
import com.oracle.report.services.interfaces.IGenerateReportService;
import com.oracle.report.services.interfaces.IReportingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 *Author - Pankaj
 *Date - 30th May 2021
 *Description - ReportingController helps perform reporting operations and is being invoked by spring on Application ready event.
 */
@Component
public class ReportingController implements ApplicationListener<ApplicationReadyEvent> {
    private final ApplicationArguments appArgs;
    @Autowired
    IReportingApplicationService reportingApplicationService;

    @Autowired
    ICustomExceptionHandler customExceptionHandler;
    @Autowired
    IGenerateReportService generateReportService;

    public ReportingController(ApplicationArguments appArgs) {
        this.appArgs = appArgs;
    }

    /**
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (applicationReadyEvent.getApplicationContext().getParent() == null) {
            try {
                //Initialize
                reportingApplicationService.initializeProcess();
                //Perform Data operations
                reportingApplicationService.computeData();
                //Generate Report
                generateReportService.generateReport();
            } catch (Exception e) {

            }
        }
    }
}
