package com.josepedevs.application;

import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
@Slf4j
public class GetAllPersonsUseCaseImpl implements Supplier<List<PersonData>> {

    private PersonRepository personRepository;

    @Override
    public List<PersonData> get() {
        log.info("calling repository");
        return personRepository.readAll();
    }
}
