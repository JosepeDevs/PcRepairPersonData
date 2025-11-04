package com.josepedevs.infra.rest;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.rest.dto.RestPersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class PostPersonRestController {

    private final Function<PersonDataDomain, PersonDataDomain> postPersonUseCase;
    private final RestPersonMapper restPersonMapper;
    private final Environment environment;

    @PostMapping("/persons")
    public ResponseEntity<Boolean> createPerson(@RequestBody PostPersonRequestDto person) {
        log.info(
                "Received in port {} request to create a person: {}",
                environment.getProperty("local.server.port"),
                person);
        postPersonUseCase.apply(restPersonMapper.map(restPersonMapper.mapToValidate(RestPersonDto.builder()
                .name(person.getName())
                .metadata(person.getMetadata())
                .nidPassport(person.getNidPassport())
                .build())));
        // in the future this will instead create an event and return nothing
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
class PostPersonRequestDto {
    private String name;
    private String nidPassport;
    private String metadata;
}
