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
    
    
	@Override
    public void createClient(UserEntity user) {
		UserEntity userEntity = new UserEntity(
	                UUID.fromString1(user.getIdUser()),
	                user.getName(),
	                user.getPsswrd(),
	                user.getEmail(),
	        );
	        userJpaRepository.save(userEntity);
		
	}
    
	///////////////queries/////////////
	@Override
	public UserEntity getClient(@Param("idClient") String idClient) {
		
		String sqlQuery =  "SELECT * FROM clients where client_id=:idClient";
		 jdbcTemplate.update(sqlQuery, idClient);
	}
	
	@Override
    public List<UserEntity> findAll(){
		
		String sqlQuery = "SELECT * FROM clients";
	}
    
	
	//////////////Commands////////////////
	@Override
	public UserEntity updateClient(@Param("idUser") String idUser, @Param("email") String email, @Param("psswrd") String psswrd,
									 @Param("name") String name, @Param("role") String role, @Param("active") boolean active) {
		
		String sqlQuery =  "UPDATE clients SET client_id=:client_id, email=:email, psswrd=:psswrd, name=:name, role=:role, active=:active ";
		
	}
	
	@Override
    public UserEntity deleteClient(@Param("active") boolean active) {
		
		String sqlQuery = "UPDATE clients SET active=:active";
	}
	



}
