package com.oracle.report.services.interfaces;


import com.oracle.report.datatypes.requestmodels.ProjectInfo;
import com.oracle.report.errorhandling.exceptions.SystemException;

import java.util.List;
import java.util.Optional;

public interface IFileProcessorService {

    public void loadFile() throws SystemException;

    public Optional<List<ProjectInfo>> getData();
}
