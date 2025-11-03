package com.josepedevs.application.service;

import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.repository.PersonRepository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonFinderService {

    private final PersonRepository personRepository;

    public Optional<PersonData> findById(String id) {
        return personRepository.searchPersonData(id);
    }
}
