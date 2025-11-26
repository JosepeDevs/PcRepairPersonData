package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
@AllArgsConstructor
public class DeletePersonUseCaseImpl implements BiConsumer<String, Boolean> {

    private PersonRepository personRepository;
    private PersonFinderService personFinder;

    @Override
    public void accept(String idPerson, Boolean isHardDelete) {
        final var existentPerson = personFinder.findByIdAndIncludeDeleted(idPerson, false);
        existentPerson.ifPresentOrElse(
                person -> {
                    if (Boolean.TRUE.equals(isHardDelete)) {
                        personRepository.deleteHardPersonData(person.getIdPerson());
                        return;
                    }
                    personRepository.logicalDeletePersonData(person.getIdPerson());
                },
                () -> {
                    throw new PersonNotFoundException(
                            "The person with the searched id was not found", "idPerson", DomainErrorStatus.NOT_FOUND);
                });
    }
}
