package com.josepedevs.infra.rest;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.rest.dto.PersonRequestDto;
import com.josepedevs.infra.rest.dto.ResponsePersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@RestController
@Slf4j
public class RestPostPersonRestController {

    private final Function<PersonDataDomain, PersonDataDomain> postPersonUseCase;
    private final RestPersonMapper restPersonMapper;
    private final Environment environment;

    @Autowired
    public RestPostPersonRestController(
            @Qualifier("postPersonUseCase") UnaryOperator<PersonDataDomain> postPersonUseCase,
            RestPersonMapper restPersonMapper,
            Environment environment) {
        this.postPersonUseCase = postPersonUseCase;
        this.environment = environment;
        this.restPersonMapper = restPersonMapper;
    }

    @PostMapping("/persons")
    public ResponseEntity<ResponsePersonDto> createPerson(@RequestBody PersonRequestDto person) {
        log.info(
                "Received in port {} request to create a person: {}",
                environment.getProperty("local.server.port"),
                person);
        final var domainPerson = postPersonUseCase.apply(restPersonMapper.map(PersonDataDto.builder()
                .name(new NameVo(person.getName()))
                .metadata(new MetadataVo(person.getMetadata()))
                .nidPassport(new NidPassportVo(person.getNidPassport()))
                .build()));
        // in the future this will instead create an event and return nothing
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponsePersonDto.builder()
                        .id(domainPerson.getIdPerson())
                        .name(domainPerson.getName())
                        .nidPassport(domainPerson.getNidPassport())
                        .metadata(domainPerson.getMetadata())
                        .build());
    }
}
