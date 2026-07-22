package com.example.springboot.springboot.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long accessTokenExpiration;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration}")
            long accessTokenExpiration
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpiration = accessTokenExpiration;
    }

    /**
     * 회원 이메일을 subject로 넣어 Access Token을 생성합니다.
     */
    public String createAccessToken(String email) {
        Date issuedAt = new Date();
        Date expiration = new Date(
                issuedAt.getTime() + accessTokenExpiration
        );

        return Jwts.builder()
                .subject(email)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    /**
     * 토큰의 서명과 만료 시간을 검증한 뒤 Claims를 반환합니다.
     *
     * 토큰이 만료되거나 변조됐다면 JJWT 예외가 발생합니다.
     */
    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 토큰 subject에 저장된 회원 이메일을 반환합니다.
     */
    public String getEmail(String token) {
        String email = parseClaims(token).getSubject();

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "JWT에 회원 이메일이 없습니다."
            );
        }

        return email;
    }

    /**
     * Access Token 만료 시간을 초 단위로 제공합니다.
     */
    public long getAccessTokenExpirationSeconds() {
        return accessTokenExpiration / 1000;
    }
}