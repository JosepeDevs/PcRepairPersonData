package com.josepdevs.Infra.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.CreatePersonData;
import com.josepdevs.Domain.dto.PersonDataDto;
import com.josepdevs.Domain.entities.PersonData;
import com.josepdevs.Domain.service.PersonEntityComposerService;

@RestController //Spring will automatically send responses as JSON, no need to set that up
public class PostPersonRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final CreatePersonData createPerson;
	//hacemos que los servicios que nos hagan falta son parte del rest controller
    private final PersonEntityComposerService compose;

	//inyectamos en el constructor, esencialmente es lo mismo que @#autowired pero así es más explicito  y creo que facilitará los mock de tests 
	public PostPersonRestController(CreatePersonData createPerson,  PersonEntityComposerService compose) {
		this.createPerson = createPerson;
		this.compose = compose;
	}
	
	@PostMapping("/persons")
    public ResponseEntity<PersonData> createPerson(@RequestBody PersonDataDto person) {
    	
			//When spring boot parses the JSON body and maps it to our entity, it will call the constructor, since we throw there our own Exceptions we do not required to validate here the data
	    	//it will be validated when constructing the object, this can throw HttpMessageNotReadableException, already handled in our GlobalExceptionHandler
    		PersonData newPerson = compose.composePersonEntityWithoutId(person);
			newPerson = createPerson.createPerson(newPerson);
	    	
	    	//in the future this will instead create an event and return nothing
	        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	    
    }

}
