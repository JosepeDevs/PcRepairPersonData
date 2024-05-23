package com.josepdevs.Application;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.dto.AuthenticationRequestDto;
import com.josepdevs.Domain.dto.AuthenticationResponseDto;
import com.josepdevs.Domain.dto.JwtToken;
import com.josepdevs.Domain.dto.RefreshToken;
import com.josepdevs.Domain.service.StringHashCheckerService;
import com.josepdevs.Domain.service.UserFinderService;

@Service
public class LoginUserAndIssueTokens {
	
	//this use case implies issuing a token and a refresh token
	private final StringHashCheckerService hashChecker;
	
    private UserFinderService userFinderService;
    

	
	public LoginUserAndIssueTokens(StringHashCheckerService hashChecker, UserFinderService userFinderService) {
		this.hashChecker = hashChecker;
    	this.userFinderService = userFinderService;
	}
	
	public AuthenticationResponseDto authenticateUser(AuthenticationRequestDto request) {
		
		String emailToCheck = request.getEmail();
		boolean emailIsValid; 
		String unhashedPsswrd = request.getPsswrd();
		
		Optional<Users> userOptional = userFinderService.findByEmail(emailToCheck);
		
		if(! userOptional.isPresent()) {
            throw new BadCredentialsException("Invalid username or password"); //Exception used by spring security
		}
		Users user = userOptional.get();
		String currentPsswrd = user.getPsswrd();
		boolean psswrdIsValid = hashChecker.verifyPassword(unhashedPsswrd, currentPsswrd);

		emailIsValid = userFinderService.findByEmail(emailToCheck).isPresent();  //email existed and a user is present, therefore TRUE.
		
		if( ! psswrdIsValid || ! emailIsValid  ) {
            throw new BadCredentialsException("Invalid username or password"); //Exception used by spring security
		} 
		
		JwtToken jwtTokenObject = new JwtToken();
		String jwtToken = "";
		
		RefreshToken refreshTokenObject = new RefreshToken();
		String refreshToken ="";
				
		return new AuthenticationResponseDto(jwtToken,refreshToken);
	}
	
	
	
}
