package com.diling;

import com.diling.configuration.Monitoring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DiLingApplication {
    private static Logger logger = LoggerFactory.getLogger(DiLingApplication.class);

    @Autowired
    private Monitoring monitoring;

    public static void main(String[] args) {
        SpringApplication.run(DiLingApplication.class, args);
    }

    @Bean
    public String initMonitoringServer() {
        logger.info("monitoring=" + monitoring.getPorts());
        return null;
    }
}
