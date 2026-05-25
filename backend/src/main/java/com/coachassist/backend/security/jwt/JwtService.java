package com.coachassist.backend.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(

            String username,

            String role
    ) {

        return Jwts.builder()

                .subject(username)

                .claim("role", role)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )

                .signWith(
                        SignatureAlgorithm.HS256,
                        secretKey                )

                .compact();
    }
    public String getUsernameFromToken(String token) {

        Claims claims = (Claims) Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        ;

        return claims.getSubject();
    }

    public boolean isValidToken(String token) {

        try{
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;

        }
        catch (Exception e){
            return false;
        }
    }

    public String getRoleFromToken(
            String token
    ) {

        Claims claims = (Claims) Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("role", String.class);
    }
}