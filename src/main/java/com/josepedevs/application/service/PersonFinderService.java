package com.josepedevs.application.service;

import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.dto.PersonDataDao;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonFinderService {

    private final PersonRepository personRepository;

    public Optional<PersonDataDao> findById(UUID id) {
        return personRepository.searchPersonData(id);
    }
}
