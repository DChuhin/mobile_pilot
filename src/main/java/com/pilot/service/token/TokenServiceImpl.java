package com.pilot.service.token;

import com.pilot.configuration.security.SecurityConstant;
import com.pilot.model.entity.User;
import com.pilot.repository.UserDao;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token Service impl
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public String getToken(String username, String password) {
        if (username == null || password == null)
            return null;
        User user = userDao.getUserByUserName(username);
        if (user != null && user.getPassword().equals(password)) {
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("userId", user.getId());
            tokenData.put("userName", user.getName());
            long createDate = new Date().getTime();
            long expireDate = createDate + SecurityConstant.TOKEN_EXPIRATION_TIME_MILLIS;
            tokenData.put("token_create_date", createDate);
            tokenData.put("token_expiration_date", expireDate);
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(new Date(expireDate));
            jwtBuilder.setClaims(tokenData);
            return jwtBuilder.signWith(SignatureAlgorithm.HS256, SecurityConstant.KEY).compact();
        } else {
            throw new AuthenticationCredentialsNotFoundException("Authentication error");
        }
    }
}
