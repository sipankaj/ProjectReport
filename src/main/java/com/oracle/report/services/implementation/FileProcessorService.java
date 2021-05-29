package com.oracle.report.services.implementation;

import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ErrorCodes;
import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.services.interfaces.IFileProcessorService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.oracle.report.constants.ApplicationConsts.FILE_SEPARATOR;

@Component
public class FileProcessorService implements IFileProcessorService {
    static Logger logger = LoggerFactory.getLogger(FileProcessorService.class);
    public static List<ProjectInfo> projectData;
    private static String fileName = "ProjectData.csv";

    @Override
    public void loadFile()  {

        try
        {
            InputStream inputFile;
            Resource resource = new ClassPathResource(fileName);
            inputFile = resource.getInputStream();
            readData(inputFile);
        }
        catch (Exception e)
        {
            logger.error("Exception - {} ",e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            //throw new SystemException(ErrorCodes.INIT_PROCESS_FAILURE,errorMessage, e);
        }
    }

    private static void readData(InputStream inputFile) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile, StandardCharsets.UTF_8));
        projectData = reader.lines().map(FileProcessorService::mapRequestObject).collect(Collectors.toList());
    }

    private static ProjectInfo mapRequestObject(String s) {
        String[] requestLine = s.split(FILE_SEPARATOR);
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setCustomerId(Integer.parseInt(requestLine[0]));
        projectInfo.setContractId(Integer.parseInt(requestLine[1]));
        projectInfo.setGeoZone(requestLine[2]);
        projectInfo.setTeamCode(requestLine[3]);
        projectInfo.setProjectCode(requestLine[4]);
        projectInfo.setBuildDuration(requestLine[5]);
        return projectInfo;
    }
}
