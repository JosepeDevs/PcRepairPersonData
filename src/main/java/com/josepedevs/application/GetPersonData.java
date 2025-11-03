package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.dto.PersonDataDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GetPersonData {

    private PersonRepository personRepository;

    private PersonFinderService personFinder;

    public PersonDataDao getPerson(UUID idPerson) {
        Optional<PersonDataDao> existentPerson = personFinder.findById(idPerson);
        return existentPerson.orElseThrow(
                () -> new PersonNotFoundException("The person with the searched id was not found", "idPerson"));
    }

    public List<PersonDataDao> getAll() {
        log.info("calling repository");
        return personRepository.readAll();
    }
}
