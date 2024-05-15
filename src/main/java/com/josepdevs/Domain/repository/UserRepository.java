package com.josepdevs.Domain.repository;

import java.util.List;
import java.util.Optional;

import com.josepdevs.Domain.dto.UserEntity;

public interface UserRepository {
	///////////////queries/////////////
	public Optional<UserEntity> searchClient(String idClient) ;
    public List<UserEntity> readAll();
		
	
	//////////////Commands////////////////
	public void updateClient(UserEntity user) ;
    public void deleteClient(String idUser, boolean active);
    public void createClient(UserEntity user);
	
}
