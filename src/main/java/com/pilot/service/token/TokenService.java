package com.pilot.service.token;

/**
 * Token Service
 */
public interface TokenService {

    /**
     * Get token
     *
     * @param username username
     * @param password password
     * @return Token
     */
    String getToken(String username, String password);

}
