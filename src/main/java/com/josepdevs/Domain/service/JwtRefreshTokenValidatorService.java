package com.josepdevs.Domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.dto.AuthenticationRequestDto;

@Service
public class JwtRefreshTokenValidatorService {

    @Value("${refresh.token.expire-length}")
    private long refreshTokenExpirationMs;

    public boolean validateRefreshToken(String token) {
        // Implement validation logic for refresh tokens
        // This could involve checking the token against a database or another form of persistence
        return true; // Placeholder, replace with actual validation logic
    }
  
}
