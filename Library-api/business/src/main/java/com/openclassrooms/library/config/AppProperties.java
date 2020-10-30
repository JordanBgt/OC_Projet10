package com.openclassrooms.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "business")
public class AppProperties {

    private String uploadsDir;
    private long loanPeriod;

    public String getUploadsDir() {
        return uploadsDir;
    }

    public void setUploadsDir(String uploadsDir) {
        this.uploadsDir = uploadsDir;
    }

    public Long getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Long loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
}
