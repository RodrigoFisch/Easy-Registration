package com.rodrigofisch.easyregistration.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Decodifica a chave Base64 vinda do application.properties
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    // Gera o token JWT
    public String generateToken(String email) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime); // Tempo de expiração

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // Verifica se o token é válido
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token); // Se o token for inválido, lançará uma exceção
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extrai o username (subject) do token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
