package com.josepedevs.infra.persistence.jpa.postgresql;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.infra.persistence.jpa.dto.PersonDataDao;
import com.josepedevs.infra.persistence.jpa.mapper.JpaPersonMapper;
import com.josepedevs.infra.persistence.jpa.repository.JpaPersonRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JpaPostgreSqlPersonRepositoryAdapterTest {

    @Mock
    private JpaPersonRepository jpaPersonRepository;

    @Mock
    private JpaPersonMapper jpaPersonMapper;

    @InjectMocks
    private JpaPostgreSqlPersonRepositoryAdapter adapter;

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

        final var result = adapter.createPersonData(personDataDomain);

        assertNotNull(result);
        assertEquals(personDataDomain.getIdPerson(), result.getIdPerson());
        assertEquals(personDataDomain.getName(), result.getName());
        verify(jpaPersonMapper, times(1)).map(personDataDomain);
        verify(jpaPersonRepository, times(1)).save(personDataDao);
    }

    @Test
    void createPersonData_GivenPersonWithNullId_ThenStillProcesses() {

        final var personWithNullId = personDataDomain.toBuilder().idPerson(null).build();
        final var daoWithNullId = personDataDao.toBuilder().idUser(null).build();
        when(jpaPersonMapper.map(personWithNullId)).thenReturn(daoWithNullId);
        when(jpaPersonRepository.save(daoWithNullId)).thenReturn(daoWithNullId);

        final var result = adapter.createPersonData(personWithNullId);

        assertNotNull(result);
        assertNull(result.getIdPerson());
        verify(jpaPersonRepository, times(1)).save(daoWithNullId);
    }

    @Test
    void createPersonData_GivenPersonWithEmptyFields_ThenStillProcesses() {

        final var personWithEmptyFields =
                personDataDomain.toBuilder().name("").metadata("").build();
        final var daoWithEmptyFields =
                personDataDao.toBuilder().name("").metadata("").build();
        when(jpaPersonMapper.map(personWithEmptyFields)).thenReturn(daoWithEmptyFields);
        when(jpaPersonRepository.save(daoWithEmptyFields)).thenReturn(daoWithEmptyFields);

        final var result = adapter.createPersonData(personWithEmptyFields);

        assertNotNull(result);
        assertEquals("", result.getName());
        verify(jpaPersonRepository, times(1)).save(daoWithEmptyFields);
    }

    @Test
    void updatePersonData_GivenValidPerson_ThenUpdatesAndReturnsTrue() {

        when(jpaPersonMapper.map(personDataDomain)).thenReturn(personDataDao);
        when(jpaPersonRepository.save(personDataDao)).thenReturn(personDataDao);
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);

        final var result = adapter.updatePersonData(personDataDomain);

        assertEquals(personDataDomain, result);
        verify(jpaPersonMapper, times(1)).map(personDataDomain);
        verify(jpaPersonRepository, times(1)).save(personDataDao);
        verify(jpaPersonMapper, times(1)).map(personDataDao);
    }

    @Test
    void updatePersonData_GivenPersonWithNullId_ThenReturnsFalse() {

        final var personWithNullId = personDataDomain.toBuilder().idPerson(null).build();
        final var daoWithNullId = personDataDao.toBuilder().idUser(null).build();
        final var updatedDomainWithNullId =
                personDataDomain.toBuilder().idPerson(null).build();

        when(jpaPersonMapper.map(personWithNullId)).thenReturn(daoWithNullId);
        when(jpaPersonRepository.save(daoWithNullId)).thenReturn(daoWithNullId);
        when(jpaPersonMapper.map(daoWithNullId)).thenReturn(updatedDomainWithNullId);

        final var result = adapter.updatePersonData(personWithNullId);

        assertEquals(updatedDomainWithNullId, result);
        verify(jpaPersonRepository, times(1)).save(daoWithNullId);
    }

    @Test
    void updatePersonData_GivenPersonWithDifferentIdAfterSave_ThenReturnsFalse() {

        final var updatedDomainWithDifferentId =
                personDataDomain.toBuilder().idPerson("different-id").build();

        when(jpaPersonMapper.map(personDataDomain)).thenReturn(personDataDao);
        when(jpaPersonRepository.save(personDataDao)).thenReturn(personDataDao);
        when(jpaPersonMapper.map(personDataDao)).thenReturn(updatedDomainWithDifferentId);

        final var result = adapter.updatePersonData(personDataDomain);

        assertEquals(updatedDomainWithDifferentId, result);
        verify(jpaPersonRepository, times(1)).save(personDataDao);
    }

    @Test
    void updatePersonData_GivenPersonWithModifiedFields_ThenUpdatesSuccessfully() {

        final var modifiedPerson = personDataDomain.toBuilder()
                .name("Jane Doe Updated")
                .metadata("updated metadata")
                .build();
        final var modifiedDao = personDataDao.toBuilder()
                .name("Jane Doe Updated")
                .metadata("updated metadata")
                .build();

        when(jpaPersonMapper.map(modifiedPerson)).thenReturn(modifiedDao);
        when(jpaPersonRepository.save(modifiedDao)).thenReturn(modifiedDao);
        when(jpaPersonMapper.map(modifiedDao)).thenReturn(modifiedPerson);

        final var result = adapter.updatePersonData(modifiedPerson);

        assertEquals(modifiedPerson, result);
        verify(jpaPersonRepository, times(1)).save(modifiedDao);
    }

    @Test
    void deleteHardPersonData_GivenValidId_ThenDeletesSuccessfully() {

        final var idToDelete = "test-id-123";
        doNothing().when(jpaPersonRepository).deleteById(idToDelete);

        adapter.deleteHardPersonData(idToDelete);

        verify(jpaPersonRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void deleteHardPersonData_GivenNullId_ThenStillCallsDelete() {

        doNothing().when(jpaPersonRepository).deleteById(null);

        adapter.deleteHardPersonData(null);

        verify(jpaPersonRepository, times(1)).deleteById(null);
    }

    @Test
    void deleteHardPersonData_GivenEmptyId_ThenStillCallsDelete() {

        final var emptyId = "";
        doNothing().when(jpaPersonRepository).deleteById(emptyId);

        adapter.deleteHardPersonData(emptyId);

        verify(jpaPersonRepository, times(1)).deleteById(emptyId);
    }

    @Test
    void logicalDeletePersonData_GivenExistingPersonNotDeleted_ThenMarksAsDeleted() {

        final var idToDelete = "test-id-123";
        final var deletedDao = personDataDao.toBuilder().deleted(true).build();

        when(jpaPersonRepository.findByIdUserAndDeleted(idToDelete, false)).thenReturn(Optional.of(personDataDao));
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);
        when(jpaPersonMapper.map(personDataDomain)).thenReturn(personDataDao);
        when(jpaPersonRepository.save(any(PersonDataDao.class))).thenReturn(deletedDao);
        when(jpaPersonMapper.map(deletedDao)).thenReturn(personDataDomain);

        adapter.logicalDeletePersonData(idToDelete);

        ArgumentCaptor<PersonDataDao> captor = ArgumentCaptor.forClass(PersonDataDao.class);
        verify(jpaPersonRepository).save(captor.capture());
        assertTrue(captor.getValue().getDeleted());
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(idToDelete, false);
    }

    @Test
    void logicalDeletePersonData_GivenNonExistingPerson_ThenThrowsPersonNotFoundException() {

        final var idToDelete = "non-existing-id";
        when(jpaPersonRepository.findByIdUserAndDeleted(idToDelete, false)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.logicalDeletePersonData(idToDelete));
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(idToDelete, false);
        verify(jpaPersonRepository, never()).save(any(PersonDataDao.class));
    }

    @Test
    void logicalDeletePersonData_GivenAlreadyDeletedPerson_ThenThrowsPersonNotFoundException() {

        final var idToDelete = "test-id-123";
        when(jpaPersonRepository.findByIdUserAndDeleted(idToDelete, false)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.logicalDeletePersonData(idToDelete));
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(idToDelete, false);
        verify(jpaPersonRepository, never()).save(any(PersonDataDao.class));
    }

    @Test
    void logicalDeletePersonData_GivenNullId_ThenThrowsPersonNotFoundException() {

        when(jpaPersonRepository.findByIdUserAndDeleted(null, false)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.logicalDeletePersonData(null));
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(null, false);
    }

    @Test
    void searchPersonDataByIdAndDeleted_GivenExistingPersonAndIncludeDeletedTrue_ThenReturnsPersonUsingFindByIdUser() {

        final var idToSearch = "test-id-123";
        when(jpaPersonRepository.findByIdUser(idToSearch)).thenReturn(Optional.of(personDataDao));
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);

        final var result = adapter.searchPersonDataByIdAndDeleted(idToSearch, true);

        assertTrue(result.isPresent());
        assertEquals(personDataDomain.getIdPerson(), result.get().getIdPerson());
        verify(jpaPersonRepository, times(1)).findByIdUser(idToSearch);
        verify(jpaPersonRepository, never()).findByIdUserAndDeleted(anyString(), anyBoolean());
    }

    @Test
    void
            searchPersonDataByIdAndDeleted_GivenExistingPersonAndIncludeDeletedFalse_ThenReturnsPersonUsingFindByIdUserAndDeleted() {

        final var idToSearch = "test-id-123";
        when(jpaPersonRepository.findByIdUserAndDeleted(idToSearch, false)).thenReturn(Optional.of(personDataDao));
        when(jpaPersonMapper.map(personDataDao)).thenReturn(personDataDomain);

        final var result = adapter.searchPersonDataByIdAndDeleted(idToSearch, false);

        assertTrue(result.isPresent());
        assertEquals(personDataDomain.getIdPerson(), result.get().getIdPerson());
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(idToSearch, false);
        verify(jpaPersonRepository, never()).findByIdUser(anyString());
    }

    @Test
    void
            searchPersonDataByIdAndDeleted_GivenNonExistingPersonAndIncludeDeletedTrue_ThenThrowsPersonNotFoundException() {

        final var idToSearch = "non-existing-id";
        when(jpaPersonRepository.findByIdUser(idToSearch)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.searchPersonDataByIdAndDeleted(idToSearch, true));
        verify(jpaPersonRepository, times(1)).findByIdUser(idToSearch);
    }

    @Test
    void
            searchPersonDataByIdAndDeleted_GivenNonExistingPersonAndIncludeDeletedFalse_ThenThrowsPersonNotFoundException() {

        final var idToSearch = "non-existing-id";
        when(jpaPersonRepository.findByIdUserAndDeleted(idToSearch, false)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.searchPersonDataByIdAndDeleted(idToSearch, false));
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(idToSearch, false);
    }

    @Test
    void searchPersonDataByIdAndDeleted_GivenNullIdAndIncludeDeletedTrue_ThenThrowsPersonNotFoundException() {

        when(jpaPersonRepository.findByIdUser(null)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.searchPersonDataByIdAndDeleted(null, true));
        verify(jpaPersonRepository, times(1)).findByIdUser(null);
    }

    @Test
    void searchPersonDataByIdAndDeleted_GivenNullIdAndIncludeDeletedFalse_ThenThrowsPersonNotFoundException() {

        assertThrows(PersonNotFoundException.class, () -> adapter.searchPersonDataByIdAndDeleted(null, false));
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(null, false);
    }

    @Test
    void searchPersonDataByIdAndDeleted_GivenDeletedPersonAndIncludeDeletedTrue_ThenReturnsDeletedPerson() {

        final var idToSearch = "test-id-123";
        final var deletedDao = personDataDao.toBuilder().deleted(true).build();
        final var deletedDomain = personDataDomain.toBuilder().build();

        when(jpaPersonRepository.findByIdUser(idToSearch)).thenReturn(Optional.of(deletedDao));
        when(jpaPersonMapper.map(deletedDao)).thenReturn(deletedDomain);

        final var result = adapter.searchPersonDataByIdAndDeleted(idToSearch, true);

        assertTrue(result.isPresent());
        assertEquals(deletedDomain.getIdPerson(), result.get().getIdPerson());
        verify(jpaPersonRepository, times(1)).findByIdUser(idToSearch);
    }

    @Test
    void searchPersonDataByIdAndDeleted_GivenDeletedPersonAndIncludeDeletedFalse_ThenThrowsPersonNotFoundException() {

        final var idToSearch = "test-id-123";
        when(jpaPersonRepository.findByIdUserAndDeleted(idToSearch, false)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> adapter.searchPersonDataByIdAndDeleted(idToSearch, false));
        verify(jpaPersonRepository, times(1)).findByIdUserAndDeleted(idToSearch, false);
    }

    @Test
    void readAll_GivenMultipleNonDeletedPersons_ThenReturnsAllNonDeletedPersons() {

        final var person1 = easyRandom.nextObject(PersonDataDao.class).toBuilder()
                .deleted(false)
                .build();
        final var person2 = easyRandom.nextObject(PersonDataDao.class).toBuilder()
                .deleted(false)
                .build();
        final var personList = List.of(person1, person2);

        final var domain1 = easyRandom.nextObject(PersonDataDomain.class);
        final var domain2 = easyRandom.nextObject(PersonDataDomain.class);

        when(jpaPersonRepository.findAllByDeleted(false)).thenReturn(personList);
        when(jpaPersonMapper.map(person1)).thenReturn(domain1);
        when(jpaPersonMapper.map(person2)).thenReturn(domain2);

        final var result = adapter.readAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(domain1));
        assertTrue(result.contains(domain2));
        verify(jpaPersonRepository, times(1)).findAllByDeleted(false);
        verify(jpaPersonMapper, times(2)).map(any(PersonDataDao.class));
    }

    @Test
    void readAll_GivenNoPersons_ThenReturnsEmptyList() {

        when(jpaPersonRepository.findAllByDeleted(false)).thenReturn(Collections.emptyList());

        final var result = adapter.readAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(jpaPersonMapper, never()).map(any(PersonDataDao.class));
    }

    @Test
    void readAll_GivenOnlyDeletedPersons_ThenReturnsEmptyList() {

        when(jpaPersonRepository.findAllByDeleted(false)).thenReturn(Collections.emptyList());

        final var result = adapter.readAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(jpaPersonRepository, times(1)).findAllByDeleted(false);
    }

    @Test
    void readAll_GivenSinglePerson_ThenReturnsSinglePersonList() {

        final var person = personDataDao;
        final var domain = personDataDomain;

        when(jpaPersonRepository.findAllByDeleted(false)).thenReturn(List.of(person));
        when(jpaPersonMapper.map(person)).thenReturn(domain);

        final var result = adapter.readAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(domain, result.getFirst());
        verify(jpaPersonRepository, times(1)).findAllByDeleted(false);
        verify(jpaPersonMapper, times(1)).map(person);
    }

    @Test
    void readAll_GivenLargeNumberOfPersons_ThenReturnsAllPersons() {

        final var personList = List.of(
                easyRandom.nextObject(PersonDataDao.class).toBuilder()
                        .deleted(false)
                        .build(),
                easyRandom.nextObject(PersonDataDao.class).toBuilder()
                        .deleted(false)
                        .build(),
                easyRandom.nextObject(PersonDataDao.class).toBuilder()
                        .deleted(false)
                        .build(),
                easyRandom.nextObject(PersonDataDao.class).toBuilder()
                        .deleted(false)
                        .build(),
                easyRandom.nextObject(PersonDataDao.class).toBuilder()
                        .deleted(false)
                        .build());

        when(jpaPersonRepository.findAllByDeleted(false)).thenReturn(personList);
        personList.forEach(
                dao -> when(jpaPersonMapper.map(dao)).thenReturn(easyRandom.nextObject(PersonDataDomain.class)));

        final var result = adapter.readAll();

        assertNotNull(result);
        assertEquals(5, result.size());
        verify(jpaPersonRepository, times(1)).findAllByDeleted(false);
        verify(jpaPersonMapper, times(5)).map(any(PersonDataDao.class));
    }
}
