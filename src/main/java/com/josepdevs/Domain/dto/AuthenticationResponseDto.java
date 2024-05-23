package com.josepdevs.Domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Class that is returned upon Authorization, contains both the token and a refreshToken
 */
@Getter
@AllArgsConstructor
@Builder
public class AuthenticationResponseDto {
    private String jwtToken;
    private String refreshToken;
}
