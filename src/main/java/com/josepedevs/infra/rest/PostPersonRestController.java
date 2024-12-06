package com.josepedevs.infra.rest;

import com.josepedevs.application.CreatePersonData;
import com.josepedevs.domain.dto.PersonDataDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //Spring will automatically send responses as JSON, no need to set that up
@AllArgsConstructor
public class PostPersonRestController {

	private final CreatePersonData createPerson;
	
	@PostMapping("/persons")
    public ResponseEntity<Boolean> createPerson(@RequestBody PersonDataDto person) {
        createPerson.createPerson(person);
        //in the future this will instead create an event and return nothing
	        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

}