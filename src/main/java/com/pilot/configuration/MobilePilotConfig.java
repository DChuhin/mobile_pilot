package com.pilot.configuration;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * Common application configuration
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@PropertySource(value = {
        "classpath:application.properties",
        "classpath:liquibase.properties",
        "classpath:jdbc.properties"
})@ComponentScan({"com.pilot"})
public class MobilePilotConfig {

    private static final Logger LOGGER = Logger.getLogger(MobilePilotConfig.class);

    /**
     * Fixes Sonar error
     */
    @PostConstruct
    public void init() {
        LOGGER.debug("Initialization of application");
    }
}