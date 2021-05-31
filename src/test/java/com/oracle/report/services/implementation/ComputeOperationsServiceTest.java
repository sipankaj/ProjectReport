package com.oracle.report.services.implementation;

import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.services.interfaces.IProcessLogService;
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
class ComputeOperationsServiceTest {
    @MockBean
    FileProcessorService fileProcessorService;
    @Autowired
    ComputeOperationsService computeOperationsService;


    @Test
    void getUniqCustomerContractRelDataSucess() {
        Mockito.when(fileProcessorService.getData()).thenReturn(getProjectInfo());
        assertDoesNotThrow(() -> {
            computeOperationsService.getUniqCustomerContractRelData();
        });
    }

    @Test
    void getUniqCustomerContractRelDataFail() {
        Mockito.when(fileProcessorService.getData()).thenReturn(null);
        assertThrows(SystemException.class, () -> {
            computeOperationsService.getUniqCustomerContractRelData();
        });
    }

    @Test
    public void getUniqCustomerGeoZoneRelDataSucess() {
        Mockito.when(fileProcessorService.getData()).thenReturn(getProjectInfo());
        assertDoesNotThrow(() -> {
            computeOperationsService.getUniqCustomerGeoZoneRelData();
        });
    }

    @Test
    public void getUniqCustomerGeoZoneRelDataFail() {
        Mockito.when(fileProcessorService.getData()).thenReturn(null);
        assertThrows(SystemException.class, () -> {
            computeOperationsService.getUniqCustomerGeoZoneRelData();
        });
    }

    @Test
    public void getBuildDurationGeoZoneRelDataSucess() {
        Mockito.when(fileProcessorService.getData()).thenReturn(getProjectInfo());
        assertDoesNotThrow(() -> {
            computeOperationsService.getBuildDurationGeoZoneRelData();
        });
    }

    @Test
    void getBuildDurationGeoZoneRelDataFail() {
        Mockito.when(fileProcessorService.getData()).thenReturn(null);
        assertThrows(SystemException.class, () -> {
            computeOperationsService.getBuildDurationGeoZoneRelData();
        });
    }

    @Test
    public void getListOfCustomerGeoZoneRelDataSucess() {
        Mockito.when(fileProcessorService.getData()).thenReturn(getProjectInfo());
        assertDoesNotThrow(() -> {
            computeOperationsService.getListOfCustomerGeoZoneRelData();
        });
    }

    @Test
    public void getListOfCustomerGeoZoneRelDataFail() {
        Mockito.when(fileProcessorService.getData()).thenReturn(null);
        assertThrows(SystemException.class, () -> {
            computeOperationsService.getListOfCustomerGeoZoneRelData();
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