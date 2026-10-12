package com.moneyManager.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private long expirationMs;

    public String generateToken(String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMs))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public boolean validateToken(String token, UserDetails subject) {
        try {
            String extracted = extractUsername(token);
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return extracted.equals(subject.getUsername()) && expiration.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}