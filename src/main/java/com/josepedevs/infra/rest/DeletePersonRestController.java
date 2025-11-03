package com.josepedevs.infra.rest;

import com.josepedevs.application.DeletePersonData;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("persons")
@AllArgsConstructor
public class DeletePersonRestController {

    private final DeletePersonData deletePerson;

    @DeleteMapping("hard/{id}")
    public ResponseEntity<Boolean> deleteHardPerson(@PathVariable("id") String id) {
        UUID idPerson = UUID.fromString(id);
        deletePerson.deleteHardPerson(idPerson);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
    }
}
