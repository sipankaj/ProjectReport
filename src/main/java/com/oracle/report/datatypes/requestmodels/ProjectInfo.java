package com.oracle.report.datatypes.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfo {

    int customerId;
    int contractId;
    String geoZone;
    String teamCode;
    String projectCode;
    int buildDuration;

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "customerId=" + customerId +
                ", contractId=" + contractId +
                ", geoZone='" + geoZone + '\'' +
                ", teamCode='" + teamCode + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", buildDuration=" + buildDuration +
                '}';
    }


}
