package com.josepedevs.infra.rest;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.rest.dto.RestPersonDto;
import com.josepedevs.infra.rest.mapper.RestPersonMapper;
import java.util.List;
import java.util.function.Supplier;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

@ExtendWith(MockitoExtension.class)
class RestGetAllPersonsControllerTest {

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    private RestGetAllPersonsController controller;

    @Mock
    private Supplier<List<PersonDataDomain>> getAllPersonsUseCase;

    @Mock
    private RestPersonMapper mapper;

    @Mock
    private Environment environment;

    @BeforeEach
    void setUp() {
        controller = new RestGetAllPersonsController(getAllPersonsUseCase, mapper, environment);
    }

    @Test
    void getAll_GivenRequestReceived_ThenReturnsOkAndList() {

        final var domainPerson = easyRandom.nextObject(PersonDataDomain.class);
        final var restPersonDto = easyRandom.nextObject(RestPersonDto.class);

        when(getAllPersonsUseCase.get()).thenReturn(List.of(domainPerson));
        when(mapper.map(domainPerson)).thenReturn(restPersonDto);

        final var result = this.controller.getAll();

        assertEquals(1, result.getBody().size());
        verify(this.getAllPersonsUseCase, times(1)).get();
    }

    @Test
    void getAll_GivenNoData_ThenReturnsOkAndEmptyList() {

        when(getAllPersonsUseCase.get()).thenReturn(List.of());

        final var result = this.controller.getAll();

        assertNotNull(result.getBody());
        assertEquals(0, result.getBody().size());
        verify(this.getAllPersonsUseCase, times(1)).get();
    }
}
