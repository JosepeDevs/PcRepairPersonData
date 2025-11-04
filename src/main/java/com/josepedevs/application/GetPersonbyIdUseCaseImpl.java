package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GetPersonbyIdUseCaseImpl implements Function<String, PersonDataDomain> {

    private PersonFinderService personFinder;

    @Override
    public PersonDataDomain apply(String idPerson) {
        final var existentPerson = personFinder.findById(idPerson);
        return existentPerson.orElseThrow(() -> new PersonNotFoundException(
                "The person with the searched id was not found", "idPerson", DomainErrorStatus.NOT_FOUND));
    }
}
