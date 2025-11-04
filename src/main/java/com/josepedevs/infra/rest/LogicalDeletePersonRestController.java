package com.josepedevs.infra.rest;

import java.util.function.BiConsumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class LogicalDeletePersonRestController {

    private final BiConsumer<String, Boolean> deleteUseCase;
    private final Environment environment;

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Boolean> deleteHardPerson(@PathVariable("id") String id) {
        log.info(
                "Received in port {} the request to delete (logical) person with id {}",
                environment.getProperty("local.server.port"),
                id);
        deleteUseCase.accept(id, false);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
    }
}
