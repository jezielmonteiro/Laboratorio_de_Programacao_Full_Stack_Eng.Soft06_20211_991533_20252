package br.com.api.controller.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${security.jwt.secret}")
    private String secretB64;
    @Value("${security.jwt.expiration}")
    private long expirationMs;

    private Key key() {
        byte[] keyBytes = Decoders.BASE64.decode(secretB64);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, Collection<? extends GrantedAuthority> auths) {
        String roles = auths.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Date now = new Date(), exp = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token);
    }
}