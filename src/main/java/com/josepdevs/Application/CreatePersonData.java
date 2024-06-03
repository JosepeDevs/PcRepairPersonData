package com.josepdevs.Application;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.josepdevs.Domain.dto.PersonDataDto;
import com.josepdevs.Domain.dto.valueobjects.Name;
import com.josepdevs.Domain.dto.valueobjects.NidPassport;
import com.josepdevs.Domain.entities.PersonData;
import com.josepdevs.Domain.repository.PersonRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreatePersonData {

    private final PersonRepository personRepository;
        
	public PersonData createPerson(PersonDataDto person){
						
		PersonData newPerson = new
				PersonData(UUID.fromString(person.getId()), new Name(person.getName()), new NidPassport(person.getNidPassport()));
		PersonData persons = personRepository.createPersonData(newPerson);
        return persons;
	}	
	
}
