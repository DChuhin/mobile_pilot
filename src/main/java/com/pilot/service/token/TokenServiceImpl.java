package com.pilot.service.token;

import com.pilot.repository.UserDao;
import com.pilot.repository.model.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final UserDao userDao;

    @Value("security.key")
    private String key;

    @Value("${security.token.expiration}")
    private Long tokenExpiration;

    @Autowired
    public TokenServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

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
            long expireDate = createDate + tokenExpiration;
            tokenData.put("token_create_date", createDate);
            tokenData.put("token_expiration_date", expireDate);
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(new Date(expireDate));
            jwtBuilder.setClaims(tokenData);
            return jwtBuilder.signWith(SignatureAlgorithm.HS256, key).compact();
        } else {
            throw new AuthenticationCredentialsNotFoundException("Authentication error");
        }
    }
}
