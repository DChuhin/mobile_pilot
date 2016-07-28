package com.pilot.service.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Token Authentication
 */
public class TokenAuthentication extends AbstractAuthenticationToken {

    private String token;

    private UserDetails principal;

    /**
     * Token Authentication
     *
     * @param token token
     */
    public TokenAuthentication(String token) {
        super(null);
        super.setAuthenticated(true);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public UserDetails getPrincipal() {
        return principal;
    }

    public void setPrincipal(UserDetails principal) {
        this.principal = principal;
    }
}
