package com.oracle.report.services.implementation;

import com.oracle.report.ProjectReportApplicationTests;
import com.oracle.report.datatypes.config.ReportProperties;
import com.oracle.report.errorhandling.exceptions.SystemException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileProcessorServiceTest extends ProjectReportApplicationTests {
    @MockBean
    ReportProperties reportProperties;
    @Autowired
    FileProcessorService fileProcessorService;
    @Test
    void loadFileSuccess() throws SystemException {
        Mockito.when(reportProperties.getReportFile()).thenReturn("ProjectData.csv");
        fileProcessorService.loadFile();
        assertEquals(fileProcessorService.getData().isPresent(),true);
    }
    @Test
    void loadFileFailure(){
        Mockito.when(reportProperties.getReportFile()).thenReturn(null);
        assertThrows(SystemException.class, () -> {
            fileProcessorService.loadFile();
        });
    }
}