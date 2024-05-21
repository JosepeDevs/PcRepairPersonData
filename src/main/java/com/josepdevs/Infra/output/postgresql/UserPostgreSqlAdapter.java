package com.josepdevs.Infra.output.postgresql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.josepdevs.Domain.dto.Users;
import com.josepdevs.Domain.repository.UserRepository;
import com.josepdevs.Infra.output.UserJpaRepository;

@Repository
public class UserPostgreSqlAdapter implements UserRepository{

    private final UserJpaRepository userJpaRepository;
	
    public UserPostgreSqlAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    
	//////////////Commands////////////////

	@Override
    public Users createUser(Users user) {
	        userJpaRepository.save(user);
	        //after save it should contain the UUID, in the future this will return bool, and the event will publish the UUID
	        return user;
	}
	
	@Override
	public boolean updateUser(Users user) {
		Users updatedUser = userJpaRepository.save(user);
	    return updatedUser.getIdUser()  !=   null    &&    updatedUser.getIdUser().equals(user.getIdUser());

	}
	
	@Override
	public boolean deleteUser(UUID idClient){
		Optional<Users> tempUser = userJpaRepository.findById(idClient);
		Users user = tempUser.orElse(null); // if there is a user, that's the value, otherwise it will be null
		user.setActive(false);
		Users deletedUser = userJpaRepository.save(user);
	    return deletedUser.getIdUser()  !=   null    &&    deletedUser.getIdUser().equals(user.getIdUser());
	}
    
	@Override
	public void deleteHardUser(UUID idClient){
		userJpaRepository.deleteById(idClient);
	}
    
    
	///////////////queries/////////////
	@Override
	public Optional<Users> searchUser(UUID idClient){
		return userJpaRepository.findById(idClient);
	}
	
	@Override
	public List<Users> readAll(){
		return userJpaRepository.findAll();
    }
		

}
