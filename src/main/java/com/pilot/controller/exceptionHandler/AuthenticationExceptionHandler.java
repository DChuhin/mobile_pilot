package com.pilot.controller.exceptionHandler;

import com.pilot.controller.model.request.Response;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.pilot.controller")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
class AuthenticationExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationExceptionHandler.class);

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response throwAuthException(AuthenticationCredentialsNotFoundException ex) {
        LOGGER.error(ex.toString(), ex);
        return new Response(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    }

}
