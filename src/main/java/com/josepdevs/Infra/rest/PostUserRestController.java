package com.josepdevs.Infra.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.CreateUser;
import com.josepdevs.Application.GetUser;
import com.josepdevs.Domain.dto.UserEntity;

@RestController //Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("users")
public class PostUserRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final CreateUser createUser;
	
	@Autowired Request request;
    
	//inyectamos el caso de uso en el constructor 
	public PostUserRestController(CreateUser createUser) {
		this.createUser = createUser;
	}
	
    @PostMapping("create")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody UserEntity user) {
    	//When spring boot parses the JSON body and maps it to our entity, it will call the constructor, since we throw there our own Exceptions we do not required to validate here the data
    	//it will be validated when constructing the object, this can throw HttpMessageNotReadableException, already handled in our GlobalExceptionHandler
    	UUID userId = UUID.fromString(id);
    	createUser.createClient(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
