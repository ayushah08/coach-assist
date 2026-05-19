package com.coachassist.backend.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "coachassistsecretkeycoachassistsecretkey123456789";

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY
                )
                .compact();
    }

    public String getUsernameFromToken(String token) {

        Claims claims = (Claims) Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        ;

        return claims.getSubject();
    }

    public boolean isValidToken(String token) {

        try{
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;

        }
        catch (Exception e){
            return false;
        }
    }
}