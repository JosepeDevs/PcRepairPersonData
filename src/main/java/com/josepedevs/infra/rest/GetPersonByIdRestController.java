package com.josepedevs.infra.rest;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.function.Function;

@RestController
@Slf4j
@AllArgsConstructor
public class GetPersonByIdRestController {

    private final Function<String, PersonData> getPersonByIdUseCase;
    private final RestPersonMapper restPersonMapper;

    private final Environment environment;

    @GetMapping(value = "persons/{id}")
    public ResponseEntity<PersonDataDto> getPerson(@PathVariable String id) {
        log.info("Received in port {} request to get person with id: {}", environment.getProperty("local.server.port"),  id);
        return ResponseEntity.status(HttpStatus.OK).body(restPersonMapper.map(getPersonByIdUseCase.apply(id)));
    }
}
