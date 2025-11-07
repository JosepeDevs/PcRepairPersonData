package com.josepedevs.application.service;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.repository.PersonRepository;
import java.util.Optional;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonFinderServiceTest {

    @InjectMocks
    private PersonFinderService personFinderService;

    @Mock
    private PersonRepository personRepository;

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void findByIdAndIncludeDeleted_GivenFoundPerson_ThenReturnsPerson(boolean isIncludeDeleted) {
        final var id = easyRandom.nextObject(String.class);
        final var expectedPerson = easyRandom.nextObject(PersonDataDomain.class);
        when(personRepository.searchPersonDataByIdAndDeleted(id, isIncludeDeleted))
                .thenReturn(Optional.of(expectedPerson));

        final var result = personFinderService.findByIdAndIncludeDeleted(id, isIncludeDeleted);

        assertTrue(result.isPresent());
        assertEquals(expectedPerson, result.get());
        verify(personRepository).searchPersonDataByIdAndDeleted(id, isIncludeDeleted);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void findByIdAndIncludeDeleted_GivenNonExistentPerson_ThenReturnsEmpty(boolean isIncludeDeleted) {
        final var id = easyRandom.nextObject(String.class);
        when(personRepository.searchPersonDataByIdAndDeleted(id, isIncludeDeleted))
                .thenReturn(Optional.empty());

        final var result = personFinderService.findByIdAndIncludeDeleted(id, isIncludeDeleted);

        assertTrue(result.isEmpty());
        verify(personRepository).searchPersonDataByIdAndDeleted(id, isIncludeDeleted);
    }
}
