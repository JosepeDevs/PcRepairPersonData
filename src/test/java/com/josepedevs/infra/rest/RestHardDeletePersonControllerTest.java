package com.josepedevs.infra.rest;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import java.util.function.BiConsumer;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

@ExtendWith(MockitoExtension.class)
class RestHardDeletePersonControllerTest {

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    private RestHardDeletePersonController controller;

    @Mock
    private BiConsumer<String, Boolean> deleteUseCase;

    @Mock
    private Environment environment;

    @BeforeEach
    void setUp() {
        controller = new RestHardDeletePersonController(deleteUseCase, environment);
    }

    @Test
    void deleteLogicalOfPerson_GivenNonExistentId_ThenThrows404() {

        final var id = easyRandom.nextObject(String.class);
        when(environment.getProperty(any())).thenReturn("8080");

        doThrow(new PersonNotFoundException("Person not found", "id", DomainErrorStatus.NOT_FOUND))
                .when(deleteUseCase)
                .accept(id, true);

        assertThrows(PersonNotFoundException.class, () -> this.controller.deleteHardPerson(id));

        verify(deleteUseCase, times(1)).accept(any(), any());
    }

    @Test
    void deleteLogicalOfPerson_GivenExistentId_ThenReturnsFoundPerson() {

        final var id = easyRandom.nextObject(String.class);
        when(environment.getProperty(any())).thenReturn("8080");

        final var result = this.controller.deleteHardPerson(id);

        assertEquals(Boolean.TRUE, result.getBody());
        verify(deleteUseCase, times(1)).accept(any(), any());
    }
}
