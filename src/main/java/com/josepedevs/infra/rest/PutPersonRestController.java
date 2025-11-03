package com.josepedevs.infra.rest;

import java.util.UUID;
import java.util.function.Function;

import com.josepedevs.domain.entities.PersonData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
@Slf4j
public class PutPersonRestController {

    private final Function<PersonData,PersonData> putPersonUseCase;
    private final Environment environment;



    //TODO check why it created a new entry instead of saying 404 cannot be updated




    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updatePerson(
            @PathVariable("id") String id, @RequestBody UpdatePersonRequestDto person) {
        log.info("Received in port {} request to update person: {} with id {}",  environment.getProperty("local.server.port"), person, id);
        putPersonUseCase.apply(PersonData.builder()
                        .idPerson(id)
                        .name(person.getName())
                        .nidPassport(person.getNidPassport())
                        .metadata(person.getMetadata())
                .build());
        // this will create an event  (in the future), instead of a return (CQRS)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
class UpdatePersonRequestDto {
    private String name;
    private String nidPassport;
    private String metadata;
}
