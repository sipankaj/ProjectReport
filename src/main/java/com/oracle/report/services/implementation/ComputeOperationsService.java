package com.oracle.report.services.implementation;

import com.oracle.report.datatypes.config.ReportProperties;
import com.oracle.report.datatypes.enums.DataOperation;
import com.oracle.report.datatypes.processlog.ProcessLogItem;
import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ErrorCodes;
import com.oracle.report.services.interfaces.IComputeOperationsService;
import com.oracle.report.services.interfaces.IFileProcessorService;
import com.oracle.report.services.interfaces.IProcessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *Author - Pankaj
 *Date - 30th May 2021
 *Description - ComputeOperationsService provides APIs to compute different operations on data.
 */
@Component
public class ComputeOperationsService implements IComputeOperationsService {
    Logger logger = LoggerFactory.getLogger(ComputeOperationsService.class);
    @Autowired
    ReportProperties reportProperties;
    @Autowired
    IFileProcessorService fileProcessorService;
    @Autowired
    IProcessLogService processLogService;

    public ComputeOperationsService() {
    }

    /**
     * @throws SystemException
     */
    @Override
    public void getUniqCustomerContractRelData() throws SystemException {
        logger.debug("Enter - ComputeOperationsService:getUniqCustomerContractRelData");
        try {
            fileProcessorService.getData().ifPresent(data -> {
                Stream<ProjectInfo> projectInfoStream = data.stream();
                if (reportProperties.isParralel()) {
                    projectInfoStream = projectInfoStream.parallel();
                }
                projectInfoStream.collect(Collectors.groupingBy(ProjectInfo::getContractId, Collectors.groupingBy(ProjectInfo::getCustomerId, Collectors.counting())))
                        .forEach((k, v) -> createLog(DataOperation.GetUniqCustomerContractRelData, k.toString(), String.valueOf(v.keySet().size())));

            });
        } catch (Exception e) {
            logger.error("Exception - {} ", e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            throw new SystemException(ErrorCodes.COMPUTE_OPERATIONS_PROCESS_FAILURE, errorMessage, e);
        }
        logger.debug("Exit - ComputeOperationsService:getUniqCustomerContractRelData");
    }

    /**
     * @throws SystemException
     */
    @Override
    public void getUniqCustomerGeoZoneRelData() throws SystemException {
        logger.debug("Enter - ComputeOperationsService:getUniqCustomerGeoZoneRelData");
        try {
            fileProcessorService.getData().ifPresent(data -> {
                Stream<ProjectInfo> projectInfoStream = data.stream();
                ;
                if (reportProperties.isParralel()) {
                    projectInfoStream = projectInfoStream.parallel();
                }
                projectInfoStream.collect(Collectors.groupingBy(ProjectInfo::getGeoZone, Collectors.groupingBy(ProjectInfo::getCustomerId, Collectors.counting())))
                        .forEach((k, v) -> createLog(DataOperation.GetUniqCustomerGeoZoneRelData, k.toString(), String.valueOf(v.keySet().size())));
            });
        } catch (Exception e) {
            logger.error("Exception - {} ", e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            throw new SystemException(ErrorCodes.COMPUTE_OPERATIONS_PROCESS_FAILURE, errorMessage, e);
        }
        logger.debug("Exit - ComputeOperationsService:getUniqCustomerGeoZoneRelData");
    }

    /**
     * @throws SystemException
     */
    @Override
    public void getBuildDurationGeoZoneRelData() throws SystemException {
        try {
            logger.debug("Enter - ComputeOperationsService:getBuildDurationGeoZoneRelData");
            fileProcessorService.getData().ifPresent(data -> {
                Stream<ProjectInfo> projectInfoStream = data.stream();
                if (reportProperties.isParralel()) {
                    projectInfoStream = projectInfoStream.parallel();
                }
                projectInfoStream.collect(Collectors.groupingBy(ProjectInfo::getGeoZone, Collectors.averagingInt(ProjectInfo::getBuildDuration)))
                        .forEach((k, v) -> createLog(DataOperation.GetBuildDurationGeoZoneRelData, k.toString(), v.toString()));
            });
        } catch (Exception e) {
            logger.error("Exception - {} ", e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            throw new SystemException(ErrorCodes.COMPUTE_OPERATIONS_PROCESS_FAILURE, errorMessage, e);
        }
        logger.debug("Exit - ComputeOperationsService:getBuildDurationGeoZoneRelData");
    }

    /**
     * @throws SystemException
     */
    @Override
    public void getListOfCustomerGeoZoneRelData() throws SystemException {
        try {
            logger.debug("Enter - ComputeOperationsService:getListOfCustomerGeoZoneRelData");
            fileProcessorService.getData().ifPresent(data -> {
                Stream<ProjectInfo> projectInfoStream = data.stream();
                if (reportProperties.isParralel()) {
                    projectInfoStream = projectInfoStream.parallel();
                }
                projectInfoStream.collect(Collectors.groupingBy(ProjectInfo::getGeoZone, Collectors.groupingBy(ProjectInfo::getCustomerId, Collectors.counting())))
                        .forEach((k, v) -> createLog(DataOperation.GetListOfCustomerGeoZoneRelData, k.toString(), v.keySet().toString()));
            });
        } catch (Exception e) {
            logger.error("Exception - {} ", e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            throw new SystemException(ErrorCodes.COMPUTE_OPERATIONS_PROCESS_FAILURE, errorMessage, e);
        }
        logger.debug("Exit - ComputeOperationsService:getListOfCustomerGeoZoneRelData");
    }

    /**
     * @param opr
     * @param key
     * @param value
     */
    private void createLog(DataOperation opr, String key, String value) {
        logger.debug("Enter - ComputeOperationsService:createLog");
        processLogService.logData(opr, new ProcessLogItem(key, value));
        logger.debug("Exit - ComputeOperationsService:createLog");
    }
}
