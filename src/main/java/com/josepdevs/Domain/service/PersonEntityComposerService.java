package com.josepdevs.Domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.josepdevs.Domain.dto.PersonDataDto;
import com.josepdevs.Domain.entities.PersonData;

@Service
public class PersonEntityComposerService {

	public PersonData composePersonEntityWithoutId(PersonDataDto personDto) {

		String nidPassport = personDto.getNidPassport();
		String idPerson = personDto.getId();
		PersonData person = new PersonData();
		
		person.setName(personDto.getName());
		person.setNidPassport(nidPassport);
		person.setIdPerson(UUID.fromString(idPerson));
		
		 return person;
		
	}
}
