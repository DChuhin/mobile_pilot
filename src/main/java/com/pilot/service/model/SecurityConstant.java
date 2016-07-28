package com.pilot.service.model;

/**
 * Security constant
 */
public class SecurityConstant {

    public static final String HEADER_SECURITY_TOKEN = "X-Pilot-Token";
    public static final long TOKEN_EXPIRATION_TIME_MILLIS = 1000 * 3600 * 24;
    public static final String KEY = "key123";

    private SecurityConstant() {
    }
}
