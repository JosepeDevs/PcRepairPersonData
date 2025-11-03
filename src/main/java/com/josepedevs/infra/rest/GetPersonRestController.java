package com.josepedevs.infra.rest;

import com.josepedevs.application.GetPersonData;
import com.josepedevs.infra.persistence.dto.PersonDataDao;
import java.util.List;
import java.util.UUID;
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
public class GetPersonRestController {

    private final GetPersonData getPerson;

    private final Environment environment;

    @GetMapping("persons")
    public ResponseEntity<List<PersonDataDao>> getAll() {
        log.info(environment.getProperty("local.server.port"));
        return ResponseEntity.status(HttpStatus.OK).body(getPerson.getAll());
    }

    @GetMapping(value = "persons/{id}")
    public ResponseEntity<PersonDataDao> getPerson(@PathVariable String id) {
        final var idPerson = UUID.fromString(id);
        return ResponseEntity.status(HttpStatus.OK).body(getPerson.getPerson(idPerson));
    }
}
