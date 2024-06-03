package com.josepdevs.Domain.service;

import org.springframework.stereotype.Service;

import com.josepdevs.Domain.dto.PersonDataDto;
import com.josepdevs.Domain.dto.PersonData;

@Service
public class PersonEntityComposerService {

	public PersonData composePersonEntityWithoutId(PersonDataDto personDto) {
		//el caso de uso llama al repositorio
		String nidPassport = personDto.getNidPassport();
		
		PersonData person = new PersonData();
		person.setName(personDto.getName());
		person.setNidPassport(nidPassport);
		
		 return PersonData.builder()
		.name(personDto.getName())
		.nidPassport(nidPassport)
		.build();
		
		
	}
}
