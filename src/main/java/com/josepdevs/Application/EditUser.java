package com.josepdevs.Application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.dto.UserDto;
import com.josepdevs.Domain.repository.UserRepository;
import com.josepdevs.Domain.service.StringHasherService;
import com.josepdevs.Domain.service.UserBuilderFromUserDtoService;


@Component
public class EditUser {
	
    private UserBuilderFromUserDtoService buildFromDto;
	
    private UserRepository userRepository;
	
    private final StringHasherService stringHasherService;

	private String password;
    
    public EditUser(UserRepository userRepository, UserBuilderFromUserDtoService buildFromDto, StringHasherService psswrdCheckerService, StringHasherService stringHasherService, String password) {
    	this.userRepository = userRepository;
    	this.buildFromDto = buildFromDto;
    	this.stringHasherService = stringHasherService;
    	this.password = password;
    }
    	
	public void updateUser(UUID userId, UserDto userDto) {
		//el caso de uso llama al repositorio y a servicios de dominio
		
		Optional<String> psswrdOptional = Optional.ofNullable(userDto.getPsswrd());
		if(psswrdOptional.isPresent()) {
			//if a new password was sent, hash it and save to the object
			String unhashedPassword = psswrdOptional.get();
			password = stringHasherService.hashPassword(unhashedPassword);
		} else {
			//if null password was sent when edditing (is not present in the optional), we get the current password already hashed (no need to re-hash)
			password = psswrdOptional.orElseGet(() -> userDto.getPsswrd());
		}
		userDto.setPsswrd(password);		

		Users user = buildFromDto.buildUserEntityWithoutId(userDto);
		user.setIdUser(userId);
		userRepository.updateUser(user);
	}

}
