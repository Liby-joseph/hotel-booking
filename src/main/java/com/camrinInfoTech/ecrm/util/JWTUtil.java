package com.camrinInfoTech.ecrm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private static final String secret_key = "my32biysecretkeyforjakkuLogin";
    private static final int EXPIRATION_TIME =   (1000* 60*1); // 1 minute

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Generate JWT Token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Payload
                .setIssuedAt(new Date()) // Token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiry time
                .signWith(key, SignatureAlgorithm.HS256) // Signing the token
                .compact();
    }

    // Validate and parse JWT Token
    public static Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
