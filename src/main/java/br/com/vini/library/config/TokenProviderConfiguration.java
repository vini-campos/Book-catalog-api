package br.com.vini.library.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProviderConfiguration {

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.key}")
    private String key;

    // generate the token
    public String generateToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return buildToken(user.getUsername());
    }

    private String buildToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // validate the token
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        // validate signature && validate expiration
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // extract token information
    private String getUsername(String token) {
        return getClaims(token).getSubject();
    }
}
