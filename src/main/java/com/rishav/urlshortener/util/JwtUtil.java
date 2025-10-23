package com.rishav.urlshortener.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {
    // Minimum 32-byte secret key (recommended)
    private static final String SECRET_KEY = "my-super-secure-and-random-jwt-secret-key-123!";

    // Or even better — generate it safely:
    private static final Key key = Keys.hmacShaKeyFor(
            "3a7f81f45f8e5e87dfb95cb08f7d6aef3b8f3c73e7db6f9e1c0d35a6a2e19d1a".getBytes()
    );
    private static final long EXPIRATION_TIME = 1000*60*60;

//    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }
}
