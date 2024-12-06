package com.josepedevs.application;

import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.domain.service.PersonFinderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@AllArgsConstructor
public class EditPersonData {
	
    private PersonRepository personRepository;
	
    private PersonFinderService personFinder;
	
	public void updatePerson(UUID idPerson, String name, String nidPassport) {
		final var existentPerson = personFinder.findById(idPerson);
		final var person = existentPerson.orElseThrow( () -> new PersonNotFoundException("The person with the searched id was not found","idPerson"));
        person.setName(new NameVo(name).getName());
        person.setNidPassport(new NidPassportVo(nidPassport).getNidPassport());
		personRepository.updatePersonData(person);
	}

}
