package com.oracle.report.services.interfaces;

import com.oracle.report.errorhandling.exceptions.SystemException;

public interface IComputeOperationsService {
    public void getUniqCustomerContractRelData() throws SystemException;

    public void getUniqCustomerGeoZoneRelData() throws SystemException;

    public void getBuildDurationGeoZoneRelData() throws SystemException;

    public void getListOfCustomerGeoZoneRelData() throws SystemException;
}
