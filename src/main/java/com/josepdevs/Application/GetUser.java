package com.josepdevs.Application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.Exceptions.UserNotFoundException;
import com.josepdevs.Domain.dto.UserEntity;
import com.josepdevs.Domain.repository.UserRepository;
import com.josepdevs.Domain.service.UserFinderService;

@Service
public class GetUser {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private UserFinderService userFinder;
	
	public UserEntity getUser(UUID idUser){
		Optional<UserEntity> existentUser = userFinder.findById(idUser);
		UserEntity user = existentUser.orElseThrow( () -> new UserNotFoundException(idUser,"The user with the searched id was not found")); 
        return user;
	}	
	
	public List<UserEntity> getAll(){
		//el caso de uso llama al repositorio
        return  userRepository.readAll();
	}	
	
}
