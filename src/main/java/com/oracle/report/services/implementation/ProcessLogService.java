package com.oracle.report.services.implementation;

import com.oracle.report.datatypes.config.ReportProperties;
import com.oracle.report.datatypes.enums.DataOperation;
import com.oracle.report.datatypes.processlog.ProcessLogItem;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.services.interfaces.IProcessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 *Author - Pankaj
 *Date - 30th May 2021
 *Description - ProcessLogService provides APIs to store and print data.
 */
@Component
public class ProcessLogService implements IProcessLogService {
    Logger logger = LoggerFactory.getLogger(ReportingApplicationService.class);
    private Map<DataOperation, List<ProcessLogItem>> processLog;
    @Autowired
    ReportProperties reportProperties;

    /**
     * @param operation
     * @param item
     */
    @Override
    public void logData(DataOperation operation, ProcessLogItem item) {
        logger.debug("Enter - ProcessLogService:logData");
        if (this.processLog == null) {
            if (reportProperties.isParralel())
                this.processLog = new ConcurrentHashMap<>();
            else
                this.processLog = new HashMap<>();
        }
        if (processLog.containsKey(operation)) {
            this.processLog.get(operation).add(item);
        } else {
            List<ProcessLogItem> processLogItems = new ArrayList<>();
            processLogItems.add(item);
            this.processLog.put(operation, processLogItems);
        }
        logger.debug("Exit - ProcessLogService:logData");
    }

    /**
     *
     */
    @Override
    public void printReport() {
        logger.debug("Enter - ProcessLogService:printReport");
        this.processLog.entrySet().stream().forEach((k) -> {
            switch (k.getKey()) {
                case GetBuildDurationGeoZoneRelData:
                    System.out.println("The average buildduration for each geozone - ");
                    System.out.println("Zone   ||  Avg BuildDuration");
                    k.getValue().stream().forEach(item -> System.out.println(item.getKey() + "      " + item.getValue()));
                    System.out.println("");
                    break;
                case GetUniqCustomerGeoZoneRelData:
                    System.out.println("The number of unique customerId for each geozone - ");
                    System.out.println("Zone    ||   No Of Customer IDs");
                    k.getValue().stream().forEach(item -> System.out.println(item.getKey() + "         " + item.getValue()));
                    System.out.println("");
                    break;
                case GetUniqCustomerContractRelData:
                    System.out.println("The number of unique customerId for each contractId. - ");
                    System.out.println("ContractId || No Of Customer IDs");
                    k.getValue().stream().forEach(item -> System.out.println(item.getKey() + "            " + item.getValue()));
                    System.out.println("");
                    break;
                case GetListOfCustomerGeoZoneRelData:
                    System.out.println(" The list of unique customerId for each geozone - ");
                    System.out.println("Zone || Customer IDs");
                    k.getValue().stream().forEach(item -> System.out.println(item.getKey() + "    " + item.getValue()));
                    System.out.println("");
                    break;
                default:
                    System.out.println("Operation not Valid!!");
            }

        });
        logger.debug("Exit - ProcessLogService:printReport");
    }
}
