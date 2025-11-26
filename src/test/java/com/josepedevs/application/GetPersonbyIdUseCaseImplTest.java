package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPersonbyIdUseCaseImplTest {

    @Mock
    private PersonFinderService personFinder;

    @InjectMocks
    private GetPersonbyIdUseCaseImpl useCase;

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @Test
    void apply_GivenNonExistentPerson_ThenThrowsPersonNotFoundException() {
        final var id = easyRandom.nextObject(String.class);
        final var isIncludedDeleted = false;

        when(personFinder.findByIdAndIncludeDeleted(id, isIncludedDeleted)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> this.useCase.apply(id));
        verify(personFinder, times(1)).findByIdAndIncludeDeleted(id, isIncludedDeleted);
    }

    @Test
    void accept_GivenExistentPersonAndDeleteMethod_ThenCallsCorrectDelete() {
        final var isIncludedDeleted = false;

        final var person = easyRandom.nextObject(PersonDataDomain.class);
        when(personFinder.findByIdAndIncludeDeleted(person.getIdPerson(), isIncludedDeleted))
                .thenReturn(Optional.of(person));
        this.useCase.apply(person.getIdPerson());

        verify(personFinder, times(1)).findByIdAndIncludeDeleted(person.getIdPerson(), isIncludedDeleted);
    }
}
