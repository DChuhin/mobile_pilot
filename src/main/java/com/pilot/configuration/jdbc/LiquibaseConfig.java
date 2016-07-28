package com.pilot.configuration.jdbc;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LiquidBase configuration
 */
@Configuration
public class LiquibaseConfig {

    @Value("${liquibase.should.run}")
    boolean liquibaseShouldRun;

    @Autowired
    private BasicDataSource dataSource;

    /**
     * Bean for liquibase config
     *
     * @return SpringLiquibase
     */
    @Bean(name = "liquibase")
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase/db.changelog.xml");
        liquibase.setShouldRun(liquibaseShouldRun);
        return liquibase;
    }
}
