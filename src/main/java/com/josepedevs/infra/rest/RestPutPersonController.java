package com.josepedevs.infra.rest;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.rest.dto.PersonRequestDto;
import com.josepedevs.infra.rest.dto.ResponsePersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persons")
@Slf4j
public class RestPutPersonController {

    private final Function<PersonDataDomain, PersonDataDomain> putPersonUseCase;
    private final RestPersonMapper restPersonMapper;
    private final Environment environment;

    @Autowired
    public RestPutPersonController(
            @Qualifier("putPersonUseCase") UnaryOperator<PersonDataDomain> putPersonUseCase,
            RestPersonMapper restPersonMapper,
            Environment environment) {
        this.putPersonUseCase = putPersonUseCase;
        this.environment = environment;
        this.restPersonMapper = restPersonMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePersonDto> updatePerson(
            @PathVariable("id") String id, @RequestBody PersonRequestDto person) {
        log.info(
                "Received in port {} request to update person: {} with id {}",
                environment.getProperty("local.server.port"),
                person,
                id);
        final var domainPerson = putPersonUseCase.apply(restPersonMapper.map(PersonDataDto.builder()
                .id(id)
                .name(new NameVo(person.getName()))
                .metadata(new MetadataVo(person.getMetadata()))
                .nidPassport(new NidPassportVo(person.getNidPassport()))
                .build()));
        // this will create an event  (in the future), instead of a return (CQRS)
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponsePersonDto.builder()
                        .id(domainPerson.getIdPerson())
                        .name(domainPerson.getName())
                        .nidPassport(domainPerson.getNidPassport())
                        .metadata(domainPerson.getMetadata())
                        .build());
    }
}
