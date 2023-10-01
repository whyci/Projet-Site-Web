package com.example.promotion.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    public String generateToken(String subject, long expirationTimeMillis) {
        Claims claims = Jwts.claims().setSubject(subject);

        String secretKey = "admin!Merca.12DONA2023";
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
                .compact();
    }
}
