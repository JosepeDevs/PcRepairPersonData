package com.josepdevs.Domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.dto.UserDto;

public interface UserRepository {
	
	///////////////queries/////////////
	public Optional<Users> searchUser(UUID idClient) ;
	public Optional<Users> searchUserByEmail(String email) ;
	public List<Users> readAll();
		
	
	//////////////Commands////////////////
	public boolean updateUser(Users user) ;
    public boolean deleteUser(UUID idUser);
    public void deleteHardUser(UUID idUser);
    public Users createUser(Users user);
	
}
