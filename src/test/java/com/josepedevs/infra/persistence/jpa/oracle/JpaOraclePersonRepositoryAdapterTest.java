package com.josepedevs.infra.persistence.jpa.oracle;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.infra.persistence.jpa.dto.PersonDataDao;
import com.josepedevs.infra.persistence.jpa.mapper.JpaPersonMapper;
import com.josepedevs.infra.persistence.jpa.repository.JpaPersonRepository;
import com.josepedevs.infra.rest.feign.RestBatchClient;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaOraclePersonRepositoryAdapterTest {

    @Mock
    private JpaPersonRepository jpaPersonRepository;

    @Mock
    private JpaPersonMapper jpaPersonMapper;

    @Mock
    private RestBatchClient restBatchClient;

    @InjectMocks
    private JpaOraclePersonRepositoryAdapter adapter;

    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    private PersonDataDomain personDataDomain;
    private PersonDataDao personDataDao;

    @BeforeEach
    void setUp() {
        personDataDomain = easyRandom.nextObject(PersonDataDomain.class).toBuilder()
                .idPerson("test-id-123")
                .name("John Doe")
                .nidPassport("12345678A")
                .metadata("{test metadata}")
                .build();

        personDataDao = easyRandom.nextObject(PersonDataDao.class).toBuilder()
                .idUser("test-id-123")
                .name("John Doe")
                .nidPassport("12345678A")
                .metadata("{test metadata}")
                .deleted(false)
                .build();
    }

    @Test
    void createPersonData_GivenValidPerson_ThenSavesAndReturnsThePerson() {
        when(jpaPersonMapper.map(personDataDomain)).thenReturn(personDataDao);
        when(jpaPersonRepository.save(personDataDao)).thenReturn(personDataDao);
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);
        doNothing().when(restBatchClient).runJobAsync();

        final var result = adapter.createPersonData(personDataDomain);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(personDataDomain.getIdPerson(), result.getIdPerson()),
                () -> assertEquals(personDataDomain.getName(), result.getName())
        );

        verify(jpaPersonMapper).map(personDataDomain);
        verify(jpaPersonRepository).save(personDataDao);
        verify(jpaPersonMapper).map(personDataDao);
        verify(restBatchClient).runJobAsync();
    }



    @Test
    void updatePersonData_GivenValidPerson_ThenUpdatesAndReturnsPerson() {
        when(jpaPersonMapper.map(personDataDomain)).thenReturn(personDataDao);
        when(jpaPersonRepository.save(personDataDao)).thenReturn(personDataDao);
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);
        doNothing().when(restBatchClient).runJobAsync();

        final var result = adapter.updatePersonData(personDataDomain);

        assertEquals(personDataDomain, result);
        verify(jpaPersonMapper).map(personDataDomain);
        verify(jpaPersonRepository).save(personDataDao);
        verify(jpaPersonMapper).map(personDataDao);
        verify(restBatchClient).runJobAsync();
    }

    @Test
    void deleteHardPersonData_GivenValidId_ThenDeletesSuccessfully() {
        final var idToDelete = "test-id-123";
        doNothing().when(jpaPersonRepository).deleteById(idToDelete);

        adapter.deleteHardPersonData(idToDelete);

        verify(jpaPersonRepository).deleteById(idToDelete);
    }

    @Test
    void logicalDeletePersonData_GivenExistingPersonNotDeleted_ThenMarksAsDeleted() {
        final var idToDelete = "test-id-123";
        final var deletedDao = personDataDao.toBuilder().deleted(true).build();

        when(jpaPersonRepository.findByIdUserAndDeleted(idToDelete, false)).thenReturn(Optional.of(personDataDao));
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);
        when(jpaPersonMapper.map(personDataDomain)).thenReturn(personDataDao);
        when(jpaPersonRepository.save(any(PersonDataDao.class))).thenReturn(deletedDao);

        adapter.logicalDeletePersonData(idToDelete);

        verify(jpaPersonRepository).save(argThat(PersonDataDao::getDeleted));
        verify(jpaPersonRepository).findByIdUserAndDeleted(idToDelete, false);
    }

    @Test
    void logicalDeletePersonData_GivenNonExistingPerson_ThenThrowsPersonNotFoundException() {
        final var idToDelete = "non-existing-id";
        when(jpaPersonRepository.findByIdUserAndDeleted(idToDelete, false)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.logicalDeletePersonData(idToDelete));
        verify(jpaPersonRepository).findByIdUserAndDeleted(idToDelete, false);
        verify(jpaPersonRepository, never()).save(any(PersonDataDao.class));
    }

    @Test
    void searchPersonDataByIdAndDeleted_GivenExistingPersonAndIncludeDeletedTrue_ThenReturnsPerson() {
        final var idToSearch = "test-id-123";
        when(jpaPersonRepository.findByIdUser(idToSearch)).thenReturn(Optional.of(personDataDao));
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);

        final var result = adapter.searchPersonDataByIdAndDeleted(idToSearch, true);

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(personDataDomain.getIdPerson(), result.get().getIdPerson())
        );

        verify(jpaPersonRepository).findByIdUser(idToSearch);
        verify(jpaPersonRepository, never()).findByIdUserAndDeleted(anyString(), anyBoolean());
    }

    @Test
    void searchPersonDataByIdAndDeleted_GivenNonExistingPerson_ThenThrowsPersonNotFoundException() {
        final var idToSearch = "non-existing-id";
        when(jpaPersonRepository.findByIdUserAndDeleted(idToSearch, false)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.searchPersonDataByIdAndDeleted(idToSearch, false));
        verify(jpaPersonRepository).findByIdUserAndDeleted(idToSearch, false);
    }

    @Test
    void readAll_GivenMultipleNonDeletedPersons_ThenReturnsAllNonDeletedPersons() {
        final var person1 = personDataDao;
        final var person2 = personDataDao.toBuilder().idUser("id-2").build();
        final var domain1 = personDataDomain;
        final var domain2 = personDataDomain.toBuilder().idPerson("id-2").build();

        when(jpaPersonRepository.findAllByDeleted(false)).thenReturn(List.of(person1, person2));
        when(jpaPersonMapper.map(person1)).thenReturn(domain1);
        when(jpaPersonMapper.map(person2)).thenReturn(domain2);

        final var result = adapter.readAll();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
                () -> assertTrue(result.contains(domain1)),
                () -> assertTrue(result.contains(domain2))
        );

        verify(jpaPersonRepository).findAllByDeleted(false);
        verify(jpaPersonMapper, times(2)).map(any(PersonDataDao.class));
    }
}
