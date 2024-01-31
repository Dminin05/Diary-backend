package com.minin.coreservice.utils;

import com.minin.coreservice.services.auth.dtos.ClaimsForToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.*;

@Component
public class JwtTokenUtils {

    @Value("${spring.security.access-secret}")
    private String accessSecret;

    @Value("${spring.security.life-time}")
    private Duration lifeTime;

    @Value("${spring.security.refresh-secret}")
    private String refreshSecret;

    @Value("${spring.security.refresh-life-time}")
    private Duration refreshLifeTime;

    public String generateAccessToken(UserDetails userDetails, ClaimsForToken claimsForToken) {

        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        claims.put("roles", roles);
        claims.put("identityId", claimsForToken.getIdentityId());
        claims.put("email", claimsForToken.getEmail());
        claims.put("isVerified", claimsForToken.isVerified());

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + lifeTime.toMillis());

        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(getAccessSecret(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + refreshLifeTime.toMillis());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(getRefreshSecret(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return getAllClaimsFromAccessToken(token).getSubject();
    }

    public String getUsernameFromRefreshToken(String token) {
        return getAllClaimsFromRefreshToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromAccessToken(token)
                .get("roles", List.class);
    }

    public UUID getIdentityIdFromAccessToken(String token) {
        return UUID.fromString(this.getAllClaimsFromAccessToken(token).get("identityId").toString());
    }

    public String getEmailFromAccessToken(String token) {
        return this.getAllClaimsFromAccessToken(token).get("email").toString();
    }

    public boolean getIsVerifiedFromAccessToken(String token) {
        return this.getAllClaimsFromAccessToken(token).get("isVerified", Boolean.class);
    }

    private Claims getAllClaimsFromAccessToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getAccessSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Claims getAllClaimsFromRefreshToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getRefreshSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getAccessSecret() {
        byte[] keyBytes = Decoders.BASE64.decode(accessSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getRefreshSecret() {
        byte[] keyBytes = Decoders.BASE64.decode(refreshSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
