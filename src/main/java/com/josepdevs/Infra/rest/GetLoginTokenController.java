package com.josepdevs.Infra.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.LoginUserAndIssueTokens;
import com.josepdevs.Domain.dto.AuthenticationRequestDto;
import com.josepdevs.Domain.dto.AuthenticationResponseDto;

@RestController
@RequestMapping("/api/v1/auth")
public class GetLoginTokenController {

	
	 	@Autowired
	    private LoginUserAndIssueTokens loginUserAndIssueTokens;

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto request) {
	        AuthenticationResponseDto response = loginUserAndIssueTokens.authenticateUser(request);
	        return ResponseEntity.ok(response);
	    }
}
