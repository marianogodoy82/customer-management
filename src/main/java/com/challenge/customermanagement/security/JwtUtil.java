package com.challenge.customermanagement.security;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
@ConfigurationProperties("jwt")
@Setter
public class JwtUtil {
   private String secret;
   private Long expiration;

   public String generateToken(String username) {
      return Jwts.builder()
                 .subject(username)
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration( Date.from(Instant.now().plus(Duration.ofMillis(expiration))))
                 .signWith(getSigningKey())
                 .compact();
   }

   private SecretKey getSigningKey() {
      byte[] keyBytes = Decoders.BASE64.decode(secret);
      return Keys.hmacShaKeyFor(keyBytes);
   }

   private Claims getClaims(String token) {
      return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
                 .parseSignedClaims(token)
                 .getPayload();
   }

   private boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   public String extractUsername(String token) {
      return getClaims(token).getSubject();
   }

   public boolean isTokenValid(String token, String username) {
      return (username.equals(extractUsername(token)) && !isTokenExpired(token));
   }

   public Date extractExpiration(String token) {
      return getClaims(token).getExpiration();
   }

}
