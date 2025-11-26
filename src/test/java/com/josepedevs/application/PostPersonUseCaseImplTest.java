package com.josepedevs.application;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.repository.PersonRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostPersonUseCaseImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PostPersonUseCaseImpl useCase;

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @Test
    void apply_GivenData_ThenCreatesPerson() {

        final var person = easyRandom.nextObject(PersonDataDomain.class).toBuilder()
                .idPerson(null)
                .nidPassport("77886644Q")
                .build();
        final var personWithId = person.toBuilder()
                .idPerson(easyRandom.nextObject(String.class))
                .nidPassport("77886644Q")
                .build();

        when(personRepository.createPersonData(any())).thenReturn(personWithId);

        final var result = this.useCase.apply(person);

        assertEquals(personWithId, result);
    }
}
