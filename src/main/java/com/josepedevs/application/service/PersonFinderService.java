package com.josepedevs.application.service;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonFinderService {

    private final PersonRepository personRepository;

    public Optional<PersonDataDomain> findByIdAndIncludeDeleted(String id, boolean isIncludeDeleted) {
        return personRepository.searchPersonDataByIdAndDeleted(id, isIncludeDeleted);
    }
}
