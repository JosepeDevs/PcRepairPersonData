package com.josepedevs.application;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.repository.PersonRepository;
import java.util.List;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GetAllPersonsUseCaseImpl implements Supplier<List<PersonDataDomain>> {

    private PersonRepository personRepository;

    @Override
    public List<PersonDataDomain> get() {
        log.info("calling repository");
        return personRepository.readAll();
    }
}
