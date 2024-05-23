package com.josepdevs.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.dto.AuthenticationRequestDto;
import com.josepdevs.Domain.dto.AuthenticationResponseDto;

@Service
public class AuthenticationService {

    @Autowired
    private UserFinderService userFinderService;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
    	
        Optional<Users> userOptional = userFinderService.findByEmail(request.getEmail());
        Users user = userOptional.get();
        if (! userOptional.isPresent() ||!user.getPsswrd().equals(request.getPsswrd())) {
            throw new BadCredentialsException("Invalid username or password"); //Exception used by spring security
        }

        String jwtToken = jwtTokenGenerator.generateToken(request);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(request);

        return new AuthenticationResponseDto(jwtToken, refreshToken);
    }
}