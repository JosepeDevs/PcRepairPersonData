package com.josepedevs.infra.rest;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.infra.rest.dto.PersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@AllArgsConstructor
@Slf4j
public class PostPersonRestController {

    private final Function<PersonData,PersonData> postPersonUseCase;
    private final RestPersonMapper restPersonMapper;
    private final Environment environment;

    @PostMapping("/persons")
    public ResponseEntity<Boolean> createPerson(@RequestBody PersonDto person) {
        log.info("Received in port {} request to create a person: {}",  environment.getProperty("local.server.port"), person);
        postPersonUseCase.apply(restPersonMapper.map(person));
        // in the future this will instead create an event and return nothing
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}
