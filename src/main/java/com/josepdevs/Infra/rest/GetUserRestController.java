package com.josepdevs.Infra.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.GetUser;
import com.josepdevs.Domain.dto.UserDto;
import com.josepdevs.Domain.dto.Users;

@RestController //Spring will automatically send responses as JSON, no need to set that up
public class GetUserRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final GetUser getUser;
	
	@Autowired UserDto userDto;
    
	//inyectamos el caso de uso en el constructor 
	public GetUserRestController(GetUser getUser) {
		this.getUser = getUser; 
	}
	
    @GetMapping("users")
    public ResponseEntity<List<Users>> getAll() {
    	return ResponseEntity.status(HttpStatus.OK).body(getUser.getAll());
    }
    
    @GetMapping(value = "users/{id}")
    public ResponseEntity<Users> getClient(@PathVariable String id, @RequestBody UserDto userDto) {
    	//el controller llama al caso de uso
    	UUID idUser = UUID.fromString(id);
    	return ResponseEntity.status(HttpStatus.OK).body(getUser.getUser(idUser));
    }
	

}
