package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PutPersonUseCaseImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonFinderService personFinder;

    @InjectMocks
    private PutPersonUseCaseImpl useCase;

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @Test
    void accept_GivenNonExistentId_ThenThrows404() {

        final var person = easyRandom.nextObject(PersonDataDomain.class).toBuilder()
                .nidPassport("77886644Q")
                .build();

        when(personFinder.findByIdAndIncludeDeleted(person.getIdPerson(), true)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> this.useCase.apply(person));
    }

    @Test
    void accept_GivenExistentId_ThenUpdatesPerson() {

        final var person = easyRandom.nextObject(PersonDataDomain.class).toBuilder()
                .nidPassport("77886644Q")
                .build();

        when(personFinder.findByIdAndIncludeDeleted(person.getIdPerson(), true)).thenReturn(Optional.of(person));

        ArgumentCaptor<PersonDataDomain> captor = ArgumentCaptor.forClass(PersonDataDomain.class);
        this.useCase.apply(person);
        verify(personRepository).updatePersonData(captor.capture());
        final var captured = captor.getValue();
        assertEquals(person, captured);
    }
}
