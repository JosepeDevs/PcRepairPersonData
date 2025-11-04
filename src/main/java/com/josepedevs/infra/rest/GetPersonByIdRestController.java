package com.josepedevs.infra.rest;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.rest.dto.RestPersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class GetPersonByIdRestController {

    private final Function<String, PersonDataDomain> getPersonByIdUseCase;
    private final RestPersonMapper restPersonMapper;

    private final Environment environment;

    // TODO not return deleted cases

    @GetMapping(value = "persons/{id}")
    public ResponseEntity<RestPersonDto> getPerson(@PathVariable String id) {
        log.info(
                "Received in port {} request to get person with id: {}",
                environment.getProperty("local.server.port"),
                id);
        return ResponseEntity.status(HttpStatus.OK).body(restPersonMapper.map(getPersonByIdUseCase.apply(id)));
    }
}
