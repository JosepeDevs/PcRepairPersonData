package com.josepdevs.Application;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.repository.UserRepository;

@Component
public class DeleteUser {
	
	@Autowired
    private UserRepository userRepository;
	
	public void deleteUser(UUID idUser) {
		//el caso de uso llama al repositorio
       userRepository.deleteUser(idUser);
	}
}
