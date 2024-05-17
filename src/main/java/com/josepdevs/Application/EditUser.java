package com.josepdevs.Application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.dto.UserEntity;
import com.josepdevs.Domain.repository.UserRepository;

@Component
public class EditUser {
	
	@Autowired
    private UserRepository userRepository;
	
	public void updateUser(UUID userId, UserEntity user) {
		//el caso de uso llama al repositorio
       userRepository.updateUser(user);
	}

}
