package com.camrinInfoTech.ecrm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

public class JWTUtil {
    private static final String SECRET_KEY = "kB2xD3v8eW0KJXzRzY3tU6Jhf7VTxzNyRjZYPWpo4GE="; // Ensure this is at least 256-bit (Base64-encoded)
    private static final int EXPIRATION_TIME = 1000 * 60 * 10; // 10 minutes

    // Generate signing key from the secret
    private static SecretKey getSigningKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    // Generate JWT Token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Payload
                .setIssuedAt(new Date()) // Token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiry time
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Signing the token
                .compact();
    }

    // Validate and parse JWT Token
    public static Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Use the same key for verification
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expired");
        }
    }
}
