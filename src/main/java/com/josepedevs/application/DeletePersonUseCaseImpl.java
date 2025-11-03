package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;

import java.util.UUID;
import java.util.function.Consumer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeletePersonUseCaseImpl implements Consumer<String> {

    private PersonRepository personRepository;
    private PersonFinderService personFinder;

    @Override
    public void accept(String idPerson) {
        final var existentPerson = personFinder.findById(idPerson);
        existentPerson.ifPresentOrElse(person -> personRepository.deleteHardPersonData(person.getIdPerson()), () -> {
            throw new PersonNotFoundException("The person with the searched id was not found", "idPerson", DomainErrorStatus.NOT_FOUND);
        });
    }
}
