package com.josepdevs.Domain.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class RefreshToken {
    private String token;
    private LocalDateTime expiration;
}