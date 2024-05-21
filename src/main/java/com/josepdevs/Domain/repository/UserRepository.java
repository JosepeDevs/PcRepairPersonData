package com.josepdevs.Domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.josepdevs.Domain.dto.UserDto;
import com.josepdevs.Domain.dto.Users;

public interface UserRepository {
	
	///////////////queries/////////////
	public Optional<Users> searchUser(UUID idClient) ;
    public List<Users> readAll();
		
	
	//////////////Commands////////////////
	public boolean updateUser(Users user) ;
    public boolean deleteUser(UUID idUser);
    public void deleteHardUser(UUID idUser);
    public Users createUser(Users user);
	
}
