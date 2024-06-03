package com.josepdevs.Domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.josepdevs.Domain.entities.PersonData;
import com.josepdevs.Domain.repository.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonFinderService {
    
	private final PersonRepository personRepository;

    public Optional<PersonData> findById(UUID id) {
        return personRepository.searchPersonData(id);
    }

}
