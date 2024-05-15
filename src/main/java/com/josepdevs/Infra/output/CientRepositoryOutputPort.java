package com.josepdevs.Infra.output;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.josepdevs.Domain.dto.UserEntity;

@Repository
public interface CientRepositoryOutputPort<ClientEntity, ID> {
	
	    Stream<ClientEntity> readAll();

	    ClientEntity readById(UUID id);

	    ClientEntity create(ClientEntity entity);

	    ClientEntity update(ClientEntity entity);

	    void deleteById(ID id);

	///////////////queries/////////////
	public ClientEntity getClient(String idUser);

    public List<ClientEntity> findAll();
    
		
	//////////////Commands////////////////
	public ClientEntity updateClient(@Param("idUser") String idUser, @Param("email") String email, @Param("psswrd") String psswrd,
									 	@Param("name") String name, @Param("role") String role, @Param("active") boolean active);

    public ClientEntity deleteClient(boolean active);
	

    public ClientEntity createClient(@Param("idUser") String idUser, @Param("email") String email, @Param("psswrd") String psswrd,
    									@Param("name") String name, @Param("role") String role, @Param("active") boolean active);
}
