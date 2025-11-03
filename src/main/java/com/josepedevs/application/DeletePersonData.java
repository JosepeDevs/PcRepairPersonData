package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.dto.PersonDataDao;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeletePersonData {

    private PersonRepository personRepository;

    private PersonFinderService personFinder;

    public void deleteHardPerson(UUID idPerson) {
        Optional<PersonDataDao> existentPerson = personFinder.findById(idPerson);
        existentPerson.ifPresentOrElse(person -> personRepository.deleteHardPersonData(person.getIdPerson()), () -> {
            throw new PersonNotFoundException("The person with the searched id was not found", "idPerson");
        });
    }
}
