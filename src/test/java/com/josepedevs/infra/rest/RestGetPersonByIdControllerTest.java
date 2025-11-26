package com.josepedevs.infra.rest;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.infra.rest.dto.RestPersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.function.Function;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestGetPersonByIdControllerTest {

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    private RestGetPersonByIdController controller;

    @Mock
    private Function<String, PersonDataDomain> getPersonByIdUseCase;

    @Mock
    private RestPersonMapper mapper;

    @Mock
    private Environment environment;

    @BeforeEach
    void setUp() {
        controller = new RestGetPersonByIdController(getPersonByIdUseCase, mapper, environment);
    }

    @Test
    void getPerson_GivenNonExistentId_ThenThrows404() {
        final var id = easyRandom.nextObject(String.class);

        when(getPersonByIdUseCase.apply(any())).thenThrow(PersonNotFoundException.class);

        assertThrows(PersonNotFoundException.class, () -> this.controller.getPerson(id));

        verify(this.getPersonByIdUseCase, times(1)).apply(any());
    }

    @Test
    void getPerson_GivenExistentId_ThenReturnsFoundPerson() {

        final var id = easyRandom.nextObject(String.class);
        final var domainPerson = easyRandom.nextObject(PersonDataDomain.class);
        final var restPersonDto = easyRandom.nextObject(RestPersonDto.class);

        when(getPersonByIdUseCase.apply(id)).thenReturn(domainPerson);
        when(mapper.map(domainPerson)).thenReturn(restPersonDto);

        final var result = this.controller.getPerson(id);

        assertEquals(restPersonDto, result.getBody());
        verify(this.getPersonByIdUseCase, times(1)).apply(any());
    }
}
