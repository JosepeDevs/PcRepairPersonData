package com.josepdevs.Infra.output;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josepdevs.Domain.Entities.Users;

public interface UserJpaRepository extends JpaRepository<Users, UUID> {
//sin implementación, solo para poder llamar a los métodos de JPARepo
	
	

	//https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
	//Just by writting the name of the method using specification from documentation, JPA will automatically build a customized query!
	public Users findByEmail(String email);

}
