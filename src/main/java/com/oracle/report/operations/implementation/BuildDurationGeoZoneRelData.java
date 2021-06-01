package com.oracle.report.operations.implementation;

import com.oracle.report.datatypes.config.ReportProperties;
import com.oracle.report.datatypes.enums.DataOperation;
import com.oracle.report.datatypes.processlog.ProcessLogItem;
import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ErrorCodes;
import com.oracle.report.operations.interfaces.IBuildDurationGeoZoneRelData;
import com.oracle.report.services.implementation.ComputeOperationsService;
import com.oracle.report.services.implementation.FileProcessorService;
import com.oracle.report.services.interfaces.IProcessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BuildDurationGeoZoneRelData implements IBuildDurationGeoZoneRelData {
    Logger logger = LoggerFactory.getLogger(BuildDurationGeoZoneRelData.class);
    @Autowired
    IProcessLogService processLogService;
    @Autowired
    ReportProperties reportProperties;
    @Override
    public DataOperation getOperationType() {
        return DataOperation.GetBuildDurationGeoZoneRelData;
    }

    @Override
    public void execute(List<ProjectInfo> projectInfoList) {

        logger.debug("Enter - -BuildDurationGeoZoneRelData:execute");
        Stream<ProjectInfo> projectInfoStream = reportProperties.isParralel() ? projectInfoList.parallelStream() : projectInfoList.stream();
        projectInfoStream.collect(Collectors.groupingBy(ProjectInfo::getGeoZone, Collectors.averagingInt(ProjectInfo::getBuildDuration)))
                .forEach((k, v) -> createLog(DataOperation.GetBuildDurationGeoZoneRelData, k.toString(), v.toString()));

        logger.debug("Exit - BuildDurationGeoZoneRelData:execute");
    }

    private void createLog(DataOperation opr, String key, String value) {
        logger.debug("Enter - BuildDurationGeoZoneRelData:createLog");
        processLogService.logData(opr, new ProcessLogItem(key, value));
        logger.debug("Exit - BuildDurationGeoZoneRelData:createLog");
    }
}
