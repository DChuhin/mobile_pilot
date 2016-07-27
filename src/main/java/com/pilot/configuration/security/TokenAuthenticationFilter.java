package com.pilot.configuration.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * TokenAuthenticationFilter
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String FILTER_URL = "/api/**";

    /**
     * Default constructor
     */
    public TokenAuthenticationFilter() {
        super(FILTER_URL);
        setAuthenticationSuccessHandler((request, response, authentication) ->
                SecurityContextHolder.getContext().setAuthentication(authentication));
        setAuthenticationFailureHandler((request, response, authenticationException) ->
                response.getOutputStream().print(authenticationException.getMessage()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(SecurityConstant.HEADER_SECURITY_TOKEN);
        Authentication userAuthenticationToken = authUserByToken(token);
        if (userAuthenticationToken == null) {
            throw new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token"));
        }
        return getAuthenticationManager().authenticate(userAuthenticationToken);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }

    private Authentication authUserByToken(String token) {
        if (token == null) {
            return null;
        }
        Authentication authToken = new TokenAuthentication(token);
        try {
            return authToken;
        } catch (Exception e) {
            logger.error("Authenticate user by token error: ", e);
        }
        return authToken;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
