package com.josepdevs.Domain.service;

import org.springframework.stereotype.Service;

import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.dto.UserDto;
import com.josepdevs.Domain.dto.valueobjects.Email;
import com.josepdevs.Domain.dto.valueobjects.Psswrd;
import com.josepdevs.Domain.dto.valueobjects.Role;

@Service
public class UserBuilderFromUserDtoService {

	public Users buildUserEntityWithoutId(UserDto userDto) {
		//el caso de uso llama al repositorio
		String email  = userDto.getEmail();
		String psswrd = userDto.getPsswrd();
		String role = userDto.getRole();
		boolean active = userDto.isActive();
		
		Users user = new Users();
		user.setEmail(email);
		user.setPsswrd(psswrd);
		user.setName(userDto.getName());
		user.setRole(role);
		user.setActive(active);
		
		 return Users.builder()
		.email(email)
		.psswrd(psswrd)
		.name(userDto.getName())
		.role(role)
		.active(active)
		.build();
		
	}
}
