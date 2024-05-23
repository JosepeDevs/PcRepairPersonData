package com.josepdevs.Domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.dto.AuthenticationRequestDto;

@Service
public class JwtRefreshTokenGeneratorService {

    @Value("${refresh.token.expire-length}")
    private long refreshTokenExpirationMs;

 
    public String generateRefreshToken(AuthenticationRequestDto request) {
        // Generate refresh token
    }

}
