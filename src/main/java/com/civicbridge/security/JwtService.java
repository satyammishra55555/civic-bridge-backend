package com.civicbridge.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long accessTokenExpiration;

    private SecretKey key;

    private final long refreshTokenExpiration =
            7L * 24 * 60 * 60 * 1000;

    // =========================================
    // INITIALIZE SECRET KEY
    // =========================================

    @PostConstruct
    public void init() {

        key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    // =========================================
    // GENERATE ACCESS TOKEN
    // =========================================

    public String generateAccessToken(String username) {

        return Jwts.builder()
                .subject(username)
                .claim("type", "ACCESS")
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + accessTokenExpiration
                        )
                )
                .signWith(key)
                .compact();
    }

    // =========================================
    // GENERATE REFRESH TOKEN
    // =========================================

    public String generateRefreshToken(String username) {

        return Jwts.builder()
                .subject(username)
                .claim("type", "REFRESH")
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + refreshTokenExpiration
                        )
                )
                .signWith(key)
                .compact();
    }

    // =========================================
    // EXTRACT USERNAME
    // =========================================

    public String extractUsername(String token) {

        return getClaims(token).getSubject();
    }

    // =========================================
    // VALIDATE ACCESS TOKEN
    // =========================================

    public boolean validateAccessToken(String token) {

        try {

            Claims claims = getClaims(token);

            String type =
                    claims.get("type", String.class);

            return "ACCESS".equals(type)
                    && !claims.getExpiration()
                    .before(new Date());

        } catch (Exception exception) {

            return false;
        }
    }

    // =========================================
    // VALIDATE REFRESH TOKEN
    // =========================================

    public boolean validateRefreshToken(String token) {

        try {

            Claims claims = getClaims(token);

            String type =
                    claims.get("type", String.class);

            // DEBUG
            System.out.println(
                    "========== REFRESH TOKEN DEBUG =========="
            );

            System.out.println(
                    "Username: " +
                            claims.getSubject()
            );

            System.out.println(
                    "Token Type: " +
                            type
            );

            System.out.println(
                    "Issued At: " +
                            claims.getIssuedAt()
            );

            System.out.println(
                    "Expiration: " +
                            claims.getExpiration()
            );

            System.out.println(
                    "Current Time: " +
                            new Date()
            );

            System.out.println(
                    "========================================="
            );

            return "REFRESH".equals(type)
                    && !claims.getExpiration()
                    .before(new Date());

        } catch (Exception exception) {

            System.out.println(
                    "========== REFRESH TOKEN ERROR =========="
            );

            exception.printStackTrace();

            System.out.println(
                    "========================================="
            );

            return false;
        }
    }

    // =========================================
    // GET CLAIMS
    // =========================================

    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}