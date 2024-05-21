package com.josepdevs.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.dto.Users;
import com.josepdevs.Domain.repository.UserRepository;

@Component
public class CreateUser {

	@Autowired
    private UserRepository userRepository;
	
	public Users createUser(Users user){
						
		Users users = userRepository.createUser(user);
        return users;
	}	
	
}
