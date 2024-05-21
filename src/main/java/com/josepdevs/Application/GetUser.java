package com.josepdevs.Application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.Exceptions.UserNotFoundException;
import com.josepdevs.Domain.dto.Users;
import com.josepdevs.Domain.repository.UserRepository;
import com.josepdevs.Domain.service.UserFinderService;

@Service
public class GetUser {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserFinderService userFinder;
	
	public Users getUser(UUID idUser){
		Optional<Users> existentUser = userFinder.findById(idUser);
		Users user = existentUser.orElseThrow( () -> new UserNotFoundException("The user with the searched id was not found",idUser)); 
        return user;
	}	
	
	public List<Users> getAll(){
		//el caso de uso llama al repositorio
        return  userRepository.readAll();
	}	
	
}
