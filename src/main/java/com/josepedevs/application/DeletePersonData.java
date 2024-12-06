package com.josepedevs.application;


import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.domain.service.PersonFinderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DeletePersonData {
	
    private PersonRepository personRepository;
	
    private PersonFinderService personFinder;
	
	public void deleteHardPerson(UUID idPerson) {
		Optional<PersonData> existentPerson = personFinder.findById(idPerson);
		existentPerson.ifPresentOrElse(
				person -> personRepository.deleteHardPersonData(person.getIdPerson()),
				() -> { throw new PersonNotFoundException("The person with the searched id was not found", "idPerson"); }
		);
	}
}
