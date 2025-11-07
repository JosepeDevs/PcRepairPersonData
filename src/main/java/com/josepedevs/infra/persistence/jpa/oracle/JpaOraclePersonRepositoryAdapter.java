package com.josepedevs.infra.persistence.jpa.oracle;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.jpa.mapper.JpaPersonMapper;
import com.josepedevs.infra.persistence.jpa.repository.JpaPersonRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("oracle")
@AllArgsConstructor
public class JpaOraclePersonRepositoryAdapter implements PersonRepository {

    private static final String PERSON_NOT_FOUND = "Person not found";

    private final JpaPersonRepository jpaPersonRepository;
    private final JpaPersonMapper jpaPersonMapper;

    // ********** COMMANDS **********//

    @Override
    public PersonDataDomain createPersonData(PersonDataDomain person) {
        jpaPersonRepository.save(jpaPersonMapper.map(person));
        return person;
    }

    @Override
    public PersonDataDomain updatePersonData(PersonDataDomain person) {
        return jpaPersonMapper.map(jpaPersonRepository.save(jpaPersonMapper.map(person)));
    }

    @Override
    public void deleteHardPersonData(String idPerson) {
        jpaPersonRepository.deleteById(idPerson);
    }

    @Override
    public void logicalDeletePersonData(String idPerson) {
        final var optPerson = searchPersonDataByIdAndDeleted(idPerson, false);
        if (optPerson.isEmpty()) {
            throw new PersonNotFoundException(PERSON_NOT_FOUND, "id", DomainErrorStatus.NOT_FOUND);
        }
        final var person = optPerson.get();
        final var personDao =
                jpaPersonMapper.map(person).toBuilder().deleted(true).build();
        jpaPersonMapper.map(jpaPersonRepository.save(personDao));
    }

    // ********** QUERIES **********//

    @Override
    public Optional<PersonDataDomain> searchPersonDataByIdAndDeleted(String idPerson, boolean isIncludeDeleted) {
        if (isIncludeDeleted) {
            return Optional.ofNullable(jpaPersonRepository
                    .findByIdUser(idPerson)
                    .map(jpaPersonMapper::map)
                    .orElseThrow(
                            () -> new PersonNotFoundException(PERSON_NOT_FOUND, "id", DomainErrorStatus.NOT_FOUND)));
        }
        return Optional.ofNullable(jpaPersonRepository
                .findByIdUserAndDeleted(idPerson, false)
                .map(jpaPersonMapper::map)
                .orElseThrow(() -> new PersonNotFoundException(PERSON_NOT_FOUND, "id", DomainErrorStatus.NOT_FOUND)));
    }

    @Override
    public List<PersonDataDomain> readAll() {
        return jpaPersonRepository.findAllByDeleted(false).stream()
                .map(jpaPersonMapper::map)
                .toList();
    }
}
