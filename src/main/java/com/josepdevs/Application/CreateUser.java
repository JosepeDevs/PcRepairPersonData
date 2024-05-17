package com.josepdevs.Application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.dto.UserEntity;
import com.josepdevs.Domain.dto.valueobjects.Email;
import com.josepdevs.Domain.dto.valueobjects.Name;
import com.josepdevs.Domain.dto.valueobjects.Psswrd;
import com.josepdevs.Domain.dto.valueobjects.Role;
import com.josepdevs.Domain.repository.UserRepository;
import com.josepdevs.Domain.service.UserFinderService;

@Component
public class CreateUser {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private UserFinderService userFinder;

	public UserEntity createUser(UserEntity user){
		UUID idUser = user.getIdUser();
		//userEntity is composed of value object, we recived and mapped the object, however lets extract the attributes to build it again so it can pass all our validations
		String email = user.getEmail().getEmail();
		String psswrd = user.getPsswrd().getPsswrd();
		String name = user.getName().getName();
		String role = user.getRole().getRole();
		boolean active = user.isActive();
		
        Optional<UserEntity> existentUser = userFinder.findById(idUser);
        if (existentUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
		
		UserEntity newUser = new UserEntity(idUser,  new Email(email), new Psswrd(psswrd), new Name(name),  new Role(role),  active);
        return newUser;
	}	
	
}
