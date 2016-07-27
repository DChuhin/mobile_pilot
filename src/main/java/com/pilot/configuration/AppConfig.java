package com.pilot.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * Common application configuration
 */
@EnableAspectJAutoProxy
@Configuration
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);

    /**
     * Fixes Sonar error
     */
    @PostConstruct
    public void init() {
        LOGGER.debug("Initialization of application");
    }
}