package com.josepedevs.application;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.repository.PersonRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPersonsUseCaseImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private GetAllPersonsUseCaseImpl useCase;

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @Test
    void get_GivenNoPersons_ThenReturnsEmptyList() {
        when(personRepository.readAll()).thenReturn(List.of());

        final var result = this.useCase.get();
        assertEquals(List.of(), result);
    }

    @Test
    void get_GivenPersons_ThenReturnsList() {
        final var person = easyRandom.nextObject(PersonDataDomain.class);
        when(personRepository.readAll()).thenReturn(List.of(person));

        final var result = this.useCase.get();

        assertEquals(List.of(person), result);
    }
}
