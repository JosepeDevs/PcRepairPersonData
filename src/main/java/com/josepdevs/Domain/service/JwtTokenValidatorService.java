package com.josepdevs.Domain.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.dto.RefreshToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service

public class JwtTokenValidatorService {
    @Value("${jwt.token.expire-length}")
    private long jwtExpirationMs;

    @Value("${MYAPP_SECRET_KEY}") //TODO crear variable de entorno con esta key y en el value poner algo seguro, complicado, rollo UUID, NO HARDCODEARLO!
    private static String SECRET_KEY;

    
    public boolean validateToken(String token) {
    	
        RefreshToken rt = userRepository.findRefreshTokenByToken(refreshToken);
        if (rt == null || rt.isExpired()) {
            throw new InvalidRefreshTokenException("Refresh token is either null or expired");
        }

        Jwts.builder()
               .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
               .build()
               .parseClaimsJws(token);
        return true;

    }


}
