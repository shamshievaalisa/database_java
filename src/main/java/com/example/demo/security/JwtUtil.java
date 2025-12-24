package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMs;

    public JwtUtil(
            @Value("${jwt.secret:THIS_IS_DEMO_SECRET_KEY_CHANGE_ME}") String secret,
            @Value("${jwt.expiration:3600000}") long expirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    /**
     * Извлекает логин (subject) из токена.
     * Не проверяет валидность токена — только парсит.
     */
    public static String extractLogin(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor("THIS_IS_DEMO_SECRET_KEY_CHANGE_ME".getBytes()))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Генерирует JWT-токен для пользователя.
     */
    public String generateToken(String username, String role) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Валидирует токен и возвращает Claims.
     * Бросает исключение, если токен недействителен.
     */
    public Claims validate(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}