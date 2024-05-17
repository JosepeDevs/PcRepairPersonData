package com.josepdevs.Infra.output.postgresql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.josepdevs.Domain.dto.UserEntity;
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
    public void createUser(UserEntity user) {
		UserEntity userEntity = new UserEntity(
	            	user.getIdUser(),
	            	user.getEmail(),
	                user.getPsswrd(),
	                user.getName(),
	                user.getRole(),
	                user.isActive()
	        );
	        userJpaRepository.save(userEntity);
	}
	
	@Override
	public void updateUser(UserEntity user) {
        userJpaRepository.save(user);
	}
	
	@Override
	public void deleteUser(UUID idClient){
		Optional<UserEntity> tempUser = userJpaRepository.findById(idClient);
		UserEntity user = tempUser.orElse(null); // if there is a user, that's the value, otherwise it will be null
		user.setActive(false);
        userJpaRepository.save(user);
	}
    
    
	///////////////queries/////////////
	@Override
	public Optional<UserEntity> searchUser(UUID idClient){
		return userJpaRepository.findById(idClient);
	}
	
	@Override
	public List<UserEntity> readAll(){
		return userJpaRepository.findAll();
    }
		

}
