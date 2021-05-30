package com.oracle.report.services.implementation;

import com.oracle.report.datatypes.config.ReportProperties;
import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ErrorCodes;
import com.oracle.report.services.interfaces.IFileProcessorService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.oracle.report.constants.ApplicationConsts.FILE_SEPARATOR;

/**
 *Author - Pankaj
 *Date - 30th May 2021
 *Description - FileProcessorService provides APIs to read and process data from csv file.
 */
@Component
public class FileProcessorService implements IFileProcessorService {
    @Autowired
    ReportProperties reportProperties;
    static Logger logger = LoggerFactory.getLogger(FileProcessorService.class);
    public static Optional<List<ProjectInfo>> projectData;

    /**
     * @throws SystemException
     */
    @Override
    public void loadFile() throws SystemException {
        logger.debug("Enter - FileProcessorService:loadFile");
        try {
            InputStream inputFile;
            Resource resource = new ClassPathResource(reportProperties.getReportFile());
            inputFile = resource.getInputStream();
            readData(inputFile, reportProperties.isParralel());
        } catch (Exception e) {
            logger.error("Exception - {} ", e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            throw new SystemException(ErrorCodes.INIT_PROCESS_FAILURE, errorMessage, e);
        }
        logger.debug("Exit - FileProcessorService:loadFile");
    }

    @Override
    public Optional<List<ProjectInfo>> getData() {
        return projectData;
    }

    private static void readData(InputStream inputFile, boolean isParallel) {
        logger.debug("Enter - FileProcessorService:readData");
        Stream<String> stream = new BufferedReader(new InputStreamReader(inputFile, StandardCharsets.UTF_8)).lines();
        if (isParallel) {
            stream = stream.parallel();
        }
        projectData = Optional.ofNullable(stream.map(FileProcessorService::mapRequestObject).collect(Collectors.toList()));
        logger.debug("Exit - FileProcessorService:readData");
    }

    private static ProjectInfo mapRequestObject(String s) {
        logger.debug("Enter - FileProcessorService:mapRequestObject");
        String[] requestLine = s.split(FILE_SEPARATOR);
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setCustomerId(Integer.parseInt(requestLine[0]));
        projectInfo.setContractId(Integer.parseInt(requestLine[1]));
        projectInfo.setGeoZone(requestLine[2]);
        projectInfo.setTeamCode(requestLine[3]);
        projectInfo.setProjectCode(requestLine[4]);
        projectInfo.setBuildDuration(Integer.parseInt(requestLine[5]));
        logger.debug("Exit - FileProcessorService:mapRequestObject");
        return projectInfo;
    }

}
