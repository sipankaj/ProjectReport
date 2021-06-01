package com.oracle.report.services.implementation;

import com.oracle.report.datatypes.config.ReportProperties;
import com.oracle.report.datatypes.enums.DataOperation;
import com.oracle.report.datatypes.processlog.ProcessLogItem;
import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;
import com.oracle.report.errorhandling.interfaces.ErrorCodes;
import com.oracle.report.operations.factories.OperationsFactory;
import com.oracle.report.services.interfaces.IComputeOperationsService;
import com.oracle.report.services.interfaces.IFileProcessorService;
import com.oracle.report.services.interfaces.IProcessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Author - Pankaj
 * Date - 30th May 2021
 * Description - ComputeOperationsService provides APIs to compute different operations on data.
 */
@Component
public class ComputeOperationsService implements IComputeOperationsService {
    Logger logger = LoggerFactory.getLogger(ComputeOperationsService.class);
    @Autowired
    ReportProperties reportProperties;
    @Autowired
    IFileProcessorService fileProcessorService;

    @Autowired
    OperationsFactory operationsFactory;

    public ComputeOperationsService() {
    }


    @Override
    public void performOpertions() throws SystemException {
        try {
            fileProcessorService.getData().ifPresent(data -> {
                Stream<DataOperation> operationStream = reportProperties.isParralel() ? Stream.of(DataOperation.values()).parallel() : Stream.of(DataOperation.values());
                operationStream.forEach(opr -> {
                    operationsFactory.getOperation(opr).execute(data);
                });

            });
        } catch (Exception e) {
            logger.error("Exception - {} ", e.getMessage());
            String errorMessage = String.format("Error During Initialiation %s", e.getMessage());
            throw new SystemException(ErrorCodes.COMPUTE_OPERATIONS_PROCESS_FAILURE, errorMessage, e);
        }
    }
}
