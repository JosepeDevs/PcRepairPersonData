package com.josepdevs.Application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josepdevs.Domain.Exceptions.PersonNotFoundException;
import com.josepdevs.Domain.entities.PersonData;
import com.josepdevs.Domain.repository.PersonRepository;
import com.josepdevs.Domain.service.PersonFinderService;

@Service
public class GetPersonData {

	@Autowired
    private PersonRepository personRepository;
	
	@Autowired
    private PersonFinderService personFinder;
	
	public PersonData getPerson(UUID idPerson){
		Optional<PersonData> existentPerson = personFinder.findById(idPerson);
		PersonData person = existentPerson.orElseThrow( () -> new PersonNotFoundException("The person with the searched id was not found",idPerson.toString())); 
        return person;
	}	
	
	public List<PersonData> getAll(){
		//el caso de uso llama al repositorio
        return  personRepository.readAll();
	}	
	
}
