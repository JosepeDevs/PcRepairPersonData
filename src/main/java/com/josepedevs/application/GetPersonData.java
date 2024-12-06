package com.josepedevs.application;

import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.domain.service.PersonFinderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetPersonData {

    private PersonRepository personRepository;
	
    private PersonFinderService personFinder;
	
	public PersonData getPerson(UUID idPerson){
		Optional<PersonData> existentPerson = personFinder.findById(idPerson);
		return existentPerson.orElseThrow( () -> new PersonNotFoundException("The person with the searched id was not found","idPerson"));
	}	
	
	public List<PersonData> getAll(){
		//el caso de uso llama al repositorio
        return  personRepository.readAll();
	}	
	
}
