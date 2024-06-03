package com.josepdevs.Infra.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.DeletePersonData;
import com.josepdevs.Domain.dto.PersonDataDto;

@RestController //Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("persons")
public class DeletePersonRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final DeletePersonData deletePerson;
	
	@Autowired PersonDataDto personDto;
    
	//inyectamos el caso de uso en el constructor 
	public DeletePersonRestController(DeletePersonData deletePerson) {
		this.deletePerson = deletePerson;
	}
	
	
	@DeleteMapping("hard/{id}")
    public ResponseEntity deleteHardPerson(@PathVariable("id") String id) {
		UUID idPerson = UUID.fromString(id);
        deletePerson.deleteHardPerson(idPerson);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);

    }

}
