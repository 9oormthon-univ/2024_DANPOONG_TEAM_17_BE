package com.munhwahansang.back.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.munhwahansang.back.config.TokenConfig;
import com.munhwahansang.back.data.dto.jwt.response.TokenCreateResponse;
import com.munhwahansang.back.validator.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenConfig tokenConfig;
    private final TokenValidator tokenValidator;

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";

    private static final String AUTHORITY = "authority";

    public TokenCreateResponse createToken(Integer userId, boolean isAdmin) {

        Date issuedAt = new Date(System.currentTimeMillis());

        String authority;

        if (isAdmin) {
            authority = ROLE_ADMIN;
        } else {
            authority = ROLE_USER;
        }

        String token = JWT.create()
                .withIssuer(tokenConfig.getIssuer())
                .withSubject(String.valueOf(userId))
                .withIssuedAt(issuedAt)
                .withExpiresAt(new Date(issuedAt.getTime() + tokenConfig.getAccessExpiration()))
                .withClaim(AUTHORITY, authority)
                .sign(Algorithm.HMAC512(tokenConfig.getSecret()));

        return new TokenCreateResponse(token);
    }

    private DecodedJWT isValidToken(String token) {
        return tokenValidator.verify(token);
    }

    public Integer getUserId(String token) {
        DecodedJWT decodedJWT = isValidToken(token);
        return Integer.valueOf(decodedJWT.getSubject());
    }

}
