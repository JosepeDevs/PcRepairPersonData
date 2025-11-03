package com.josepedevs.infra.rest;

import java.util.UUID;
import java.util.function.Consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("persons")
@AllArgsConstructor
@Slf4j
public class DeletePersonRestController {

    private final Consumer<String> deleteUseCase;
    private final Environment environment;

    @DeleteMapping("persons/hard/{id}")
    public ResponseEntity<Boolean> deleteHardPerson(@PathVariable("id") String id) {
        log.info("Received in port {} the request to delete person with id", environment.getProperty("local.server.port"), id);
        deleteUseCase.accept(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
    }
}
