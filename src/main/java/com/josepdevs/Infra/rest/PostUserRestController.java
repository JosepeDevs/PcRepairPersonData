package com.josepdevs.Infra.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.CreateUser;
import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.dto.UserDto;
import com.josepdevs.Domain.service.UserBuilderFromUserDtoService;

@RestController //Spring will automatically send responses as JSON, no need to set that up
public class PostUserRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final CreateUser createUser;
	//hacemos que los servicios que nos hagan falta son parte del rest controller
    private final UserBuilderFromUserDtoService compose;

	//inyectamos en el constructor, esencialmente es lo mismo que @#autowired pero así es más explicito  y creo que facilitará los mock de tests 
	public PostUserRestController(CreateUser createUser,  UserBuilderFromUserDtoService compose) {
		this.createUser = createUser;
		this.compose = compose;
	}
	
	@PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody UserDto user) {
    	
			//When spring boot parses the JSON body and maps it to our entity, it will call the constructor, since we throw there our own Exceptions we do not required to validate here the data
	    	//it will be validated when constructing the object, this can throw HttpMessageNotReadableException, already handled in our GlobalExceptionHandler
    		Users newUser = compose.buildUserEntityWithoutId(user);
			newUser = createUser.createUser(newUser);
	    	
	    	//in the future this will instead create an event and return nothing
	        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	    
    }

}
