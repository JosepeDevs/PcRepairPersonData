package com.josepedevs.domain.service;

import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.dto.PersonData;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonFinderService {

    private final PersonRepository personRepository;

    public Optional<PersonData> findById(UUID id) {
        return personRepository.searchPersonData(id);
    }
}
