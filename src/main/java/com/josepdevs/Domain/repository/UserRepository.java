package com.josepdevs.Domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.josepdevs.Domain.dto.UserEntity;

public interface UserRepository {
	
	///////////////queries/////////////
	public Optional<UserEntity> searchUser(UUID idClient) ;
    public List<UserEntity> readAll();
		
	
	//////////////Commands////////////////
	public void updateUser(UserEntity user) ;
    public void deleteUser(UUID idUser);
    public void createUser(UserEntity user);
	
}
