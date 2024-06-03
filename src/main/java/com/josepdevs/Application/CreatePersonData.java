package com.josepdevs.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.entities.PersonData;
import com.josepdevs.Domain.repository.PersonRepository;

@Component
public class CreatePersonData {

	@Autowired
    private PersonRepository personRepository;
	
	public PersonData createPerson(PersonData person){
						
		PersonData persons = personRepository.createPersonData(person);
        return persons;
	}	
	
}
