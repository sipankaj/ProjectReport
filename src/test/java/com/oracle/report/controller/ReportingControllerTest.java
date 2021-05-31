package com.oracle.report.controller;

import com.oracle.report.ProjectReportApplicationTests;
import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.services.implementation.FileProcessorService;
import com.oracle.report.services.interfaces.IFileProcessorService;
import com.oracle.report.services.interfaces.IGenerateReportService;
import com.oracle.report.services.interfaces.IReportingApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportingControllerTest {

    @Test
    void onApplicationEvent() throws SystemException {

    }

}