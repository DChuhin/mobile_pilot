package com.pilot;

import com.pilot.configuration.MobilePilotConfig;
import org.springframework.boot.SpringApplication;

public class MobilePilotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobilePilotConfig.class, args);
    }
}
