package com.josepedevs.infra.rest;

import com.josepedevs.application.EditPersonData;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("persons")
@AllArgsConstructor
public class PutPersonRestController {

    private final EditPersonData editPerson;

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updatePerson(@PathVariable("id") String id, @RequestBody UpdatePesonRequestDto person) {
        final var personId = UUID.fromString(id);
        editPerson.updatePerson(
                personId,
                person.getName(),
                person.getNidPassport()); // Implement the update logic in your service layer

        // this will create an event  (in the future), instead of a return (CQRS)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
class UpdatePesonRequestDto {

    private String name;
    private String nidPassport;
}
