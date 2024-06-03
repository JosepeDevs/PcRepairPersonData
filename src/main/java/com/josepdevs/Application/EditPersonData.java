package com.josepdevs.Application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josepdevs.Domain.Exceptions.PersonNotFoundException;
import com.josepdevs.Domain.dto.valueobjects.Name;
import com.josepdevs.Domain.dto.valueobjects.NidPassport;
import com.josepdevs.Domain.entities.PersonData;
import com.josepdevs.Domain.repository.PersonRepository;
import com.josepdevs.Domain.service.PersonEntityComposerService;
import com.josepdevs.Domain.service.PersonFinderService;


@Component
public class EditPersonData {
	
	@Autowired
    private PersonRepository personRepository;
	
	@Autowired
    private PersonEntityComposerService compose;
	
	@Autowired
    private PersonFinderService personFinder;
	
	public void updatePerson(UUID idPerson, String name, String nidPassport) {
		//el caso de uso llama al repositorio
		Optional<PersonData> existentPerson = personFinder.findById(idPerson);
		PersonData person = existentPerson.orElseThrow( () -> new PersonNotFoundException("The person with the searched id was not found","idPerson")); 
        person.setName(new Name(name).getName());
        person.setNidPassport(new NidPassport(nidPassport).getNidPassport());
		personRepository.updatePersonData(person);
	}

}
