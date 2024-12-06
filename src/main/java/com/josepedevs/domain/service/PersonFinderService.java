package com.josepedevs.domain.service;

import java.util.Optional;
import java.util.UUID;

import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonFinderService {
    
	private final PersonRepository personRepository;

    public Optional<PersonData> findById(UUID id) {
        return personRepository.searchPersonData(id);
    }

}
