package com.oracle.report.datatypes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.oracle.reporting")
public class ReportProperties {
    private boolean parallelEnabled;
    private String reportFile;

    public boolean isParralel() {
        return parallelEnabled;
    }

    public void setParallelEnabled(boolean parallelEnabled) {
        this.parallelEnabled = parallelEnabled;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

}
