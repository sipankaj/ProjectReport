package com.oracle.report.services.implementation;


import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.services.interfaces.IDataValidatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DataValidatorServiceTest {
    @MockBean
    FileProcessorService fileProcessorService;
    @Autowired
    IDataValidatorService dataValidatorService;

    @Test
    void validateSuccess() {
        Mockito.when(fileProcessorService.getData()).thenReturn(getProjectInfo());
        assertDoesNotThrow(() -> {
            dataValidatorService.validate();
        });
    }

    @Test
    void validateFailure() {
        Mockito.when(fileProcessorService.getData()).thenReturn(Optional.ofNullable(null));
        assertThrows(SystemException.class, () -> {
            dataValidatorService.validate();
        });
    }

    private Optional<List<ProjectInfo>> getProjectInfo() {
        ProjectInfo projectInfo = ProjectInfo.builder().customerId(1234).
                contractId(1245).geoZone("geoZone").teamCode("tem1234")
                .projectCode("0909").buildDuration(5).build();

        List<ProjectInfo> projectInfoList = new ArrayList<>();
        projectInfoList.add(projectInfo);
        projectInfoList.add(projectInfo);
        projectInfoList.add(projectInfo);

        return Optional.ofNullable(projectInfoList);
    }
}