package com.pilot.controller;

import com.pilot.service.token.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Token controller
 */
@RestController
@Api(tags = "Endpoint for token generation")
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
    @ApiOperation(value = "getToken", notes = "Generate token based on credentials", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "User's name", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "User's password", required = true, dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 403, message = "Authentication Error"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/token")
    public Map<String, Object> getToken(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenService.getToken(username, password));
        return result;
    }
}
