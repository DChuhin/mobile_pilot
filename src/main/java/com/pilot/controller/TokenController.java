package com.pilot.controller;

import com.pilot.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Token controller
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * Get token
     *
     * @param username username
     * @param password password
     * @return token
     */
    @RequestMapping(value = "/token")
    public String getToken(String username, String password) {
        return tokenService.getToken(username, password);
    }
}
