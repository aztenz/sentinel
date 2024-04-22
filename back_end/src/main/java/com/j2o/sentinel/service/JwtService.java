package com.j2o.sentinel.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY_PATH = "${JWT_SECRET_KEY}";

    private static final String JWT_DURATION_PATH = "${JWT_DURATION}";

    @Value(SECRET_KEY_PATH)
    private String SECRET_KEY;

    @Value(JWT_DURATION_PATH)
    private int JWT_DURATION;

    public <T> T extractClaim(
            String token,
            Function<Claims, T> resolver
    ) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public boolean isValid(
            String token,
            UserDetails userDetails
    ) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateToken(String username) {
        return Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_DURATION))
                .signWith(getSignInKey())
                .compact();
    }

    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseEncryptedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
