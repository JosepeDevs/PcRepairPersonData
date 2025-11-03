package com.josepedevs.infra.rest;

import com.josepedevs.domain.dto.PersonDataDto;

import java.util.List;
import java.util.function.Supplier;

import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class GetAllPersonsRestController {

    private final Supplier<List<PersonData>> getAllPersonsUseCase;
    private final RestPersonMapper restPersonMapper;
    private final Environment environment;

    @GetMapping("persons")
    public ResponseEntity<List<PersonDataDto>> getAll() {
        log.info("Received in port {} the request to get all persons", environment.getProperty("local.server.port"));
        return ResponseEntity.status(HttpStatus.OK).body(getAllPersonsUseCase.get().stream().map(restPersonMapper::map).toList());
    }

}
