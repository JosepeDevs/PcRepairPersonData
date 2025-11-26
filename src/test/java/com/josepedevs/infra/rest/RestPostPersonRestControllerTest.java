package com.josepedevs.infra.rest;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.rest.dto.PersonRequestDto;
import com.josepedevs.infra.rest.dto.ResponsePersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.function.UnaryOperator;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestPostPersonRestControllerTest {

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    private RestPostPersonRestController controller;

    @Mock
    private UnaryOperator<PersonDataDomain> postPersonUseCase;

    @Mock
    private RestPersonMapper mapper;

    @Mock
    private Environment environment;

    @BeforeEach
    void setUp() {
        controller = new RestPostPersonRestController(postPersonUseCase, mapper, environment);
    }

    @Test
    void createPerson_GivenValidRequest_ThenReturnsTrueAndCreates() {

        final var inputPerson = easyRandom.nextObject(PersonRequestDto.class).toBuilder()
                .nidPassport("74747474W")
                .build();
        final var domainPerson = easyRandom.nextObject(PersonDataDomain.class);
        final var responsePerson = ResponsePersonDto.builder()
                .id(domainPerson.getIdPerson())
                .name(domainPerson.getName())
                .nidPassport(domainPerson.getNidPassport())
                .metadata(domainPerson.getMetadata())
                .build();

        when(environment.getProperty(any())).thenReturn("8080");

        when(mapper.map(any(PersonDataDto.class))).thenReturn(domainPerson);
        when(postPersonUseCase.apply(domainPerson)).thenReturn(domainPerson);

        final var result = this.controller.createPerson(inputPerson);

        assertEquals(responsePerson, result.getBody());
        verify(this.postPersonUseCase, times(1)).apply(any());
    }
}
