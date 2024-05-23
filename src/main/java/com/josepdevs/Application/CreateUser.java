package com.josepdevs.Application;

import org.springframework.stereotype.Component;

import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.repository.UserRepository;
import com.josepdevs.Domain.service.StringHasherService;

@Component
public class CreateUser {

    private UserRepository userRepository;
	
    public CreateUser(UserRepository userRepository, StringHasherService psswrdCheckerService, StringHasherService stringHasherService, String password) {
    	this.userRepository = userRepository;
    }
    
	public Users createUser(Users user){
		Users userWithUUID = userRepository.createUser(user);
        return userWithUUID;
	}	
	
}
