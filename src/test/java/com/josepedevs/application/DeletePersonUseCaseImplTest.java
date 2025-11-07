package com.josepedevs.application;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import java.util.Optional;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePersonUseCaseImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonFinderService personFinder;

    @InjectMocks
    private DeletePersonUseCaseImpl useCase;

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @Test
    void accept_GivenNonExistentPerson_ThenThrowsPersonNotFoundException() {
        final var id = easyRandom.nextObject(String.class);
        final var isIncludedDeleted = false;
        final var isHardDelete = true;

        when(personFinder.findByIdAndIncludeDeleted(id, isIncludedDeleted)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> this.useCase.accept(id, isHardDelete));
        verify(personFinder, times(1)).findByIdAndIncludeDeleted(id, isIncludedDeleted);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void accept_GivenExistentPersonAndDeleteMethod_ThenCallsCorrectDelete(boolean isHardDelete) {
        final var isIncludedDeleted = false;

        final var person = easyRandom.nextObject(PersonDataDomain.class);
        when(personFinder.findByIdAndIncludeDeleted(person.getIdPerson(), isIncludedDeleted))
                .thenReturn(Optional.of(person));
        this.useCase.accept(person.getIdPerson(), isHardDelete);

        verify(personFinder, times(1)).findByIdAndIncludeDeleted(person.getIdPerson(), isIncludedDeleted);

        if (isHardDelete) {
            verify(personRepository, times(1)).deleteHardPersonData(any());
        } else {
            verify(personRepository, times(1)).logicalDeletePersonData(any());
        }
    }
}
