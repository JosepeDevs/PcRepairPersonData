package com.josepdevs.Application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.dto.UserDto;
import com.josepdevs.Domain.dto.Users;
import com.josepdevs.Domain.repository.UserRepository;
import com.josepdevs.Domain.service.UserEntityComposerService;


@Component
public class EditUser {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserEntityComposerService compose;
	
	public void updateUser(UUID userId, UserDto userDto) {
		//el caso de uso llama al repositorio
		Users user = compose.composeUserEntityWithoutId(userDto);
		user.setIdUser(userId);
       userRepository.updateUser(user);
	}

}
