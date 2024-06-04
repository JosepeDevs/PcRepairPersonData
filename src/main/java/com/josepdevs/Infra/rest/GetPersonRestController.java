package com.josepdevs.Infra.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.GetPersonData;
import com.josepdevs.Domain.dto.PersonDataDto;
import com.josepdevs.Domain.entities.PersonData;

@RestController //Spring will automatically send responses as JSON, no need to set that up
public class GetPersonRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final GetPersonData getPerson;
	
	@Autowired PersonDataDto personDto;
	
	@Autowired Environment enviroment;
    
	//inyectamos el caso de uso en el constructor 
	public GetPersonRestController(GetPersonData getPerson) {
		this.getPerson = getPerson; 
	}
	
    @GetMapping("persons")
    public ResponseEntity<List<PersonData>> getAll() {
    	System.out.println(enviroment.getProperty("local.server.port")); 
    	return ResponseEntity.status(HttpStatus.OK).body(getPerson.getAll());
    }
    
    @GetMapping(value = "persons/{id}")
    public ResponseEntity<PersonData> getPerson(@PathVariable String id) {
    	//el controller llama al caso de uso
    	UUID idPerson = UUID.fromString(id);
    	return ResponseEntity.status(HttpStatus.OK).body(getPerson.getPerson(idPerson));
    }
	

}
