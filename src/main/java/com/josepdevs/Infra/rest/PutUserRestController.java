package com.josepdevs.Infra.rest;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.EditUser;
import com.josepdevs.Domain.dto.UserDto;

@RestController //Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("users")
public class PutUserRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final EditUser editUser;
	
	@Autowired UserDto userDto;
    
	//inyectamos el caso de uso en el constructor 
	public PutUserRestController(EditUser editUser) {
		this.editUser = editUser;
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody UserDto user) {
    	//When spring boot parses the JSON body and maps it to our entity, it will call the constructor, since we throw there our own Exceptions we do not required to validate here the data
    	//it will be validated when constructing the object, this can throw HttpMessageNotReadableException, already handled in our GlobalExceptionHandler
    	UUID userId = UUID.fromString(id);
        editUser.updateUser(userId, user); // Implement the update logic in your service layer
        
        //this will create an event  (in the future), instead of a return (CQRS)
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
