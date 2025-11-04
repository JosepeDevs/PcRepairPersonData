package com.josepedevs.infra.persistence.jpa.postgresql;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.MyRuntimeException;
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
@Profile({"devneon", "proaws"})
@AllArgsConstructor
public class JpaPostgreSqlPersonRepositoryAdapter implements PersonRepository {

    private final JpaPersonRepository jpaPersonRepository;
    private final JpaPersonMapper jpaPersonMapper;

    // ********** Commands **********//

    @Override
    public PersonDataDomain createPersonData(PersonDataDomain person) {
        jpaPersonRepository.save(jpaPersonMapper.map(person));
        return person;
    }

    @Override
    public boolean updatePersonData(PersonDataDomain person) {
        final var updatedPerson = jpaPersonRepository.save(jpaPersonMapper.map(person));
        return updatedPerson.getIdPerson() != null
                && updatedPerson.getIdPerson().equals(person.getIdPerson());
    }

    @Override
    public void deleteHardPersonData(String idPerson) {
        jpaPersonRepository.deleteById(idPerson);
    }

    @Override
    public void logicalDeletePersonData(String idPerson) {
        final var optPerson = searchPersonData(idPerson);
        if (optPerson.isEmpty()) {
            throw new PersonNotFoundException("Person not found", "id", DomainErrorStatus.NOT_FOUND);
        }
        final var person = optPerson.get();
        final var personDao =
                jpaPersonMapper.map(person).toBuilder().deleted(true).build();
        jpaPersonMapper.map(jpaPersonRepository.save(personDao));
    }

    // ********** queries **********//
    @Override
    public Optional<PersonDataDomain> searchPersonData(String idPerson) {
        return Optional.ofNullable(jpaPersonRepository
                .findById(idPerson)
                .map(jpaPersonMapper::map)
                .orElseThrow(() -> new MyRuntimeException("Person not found", "id", DomainErrorStatus.NOT_FOUND)));
    }

    @Override
    public List<PersonDataDomain> readAll() {
        return jpaPersonRepository.findAll().stream().map(jpaPersonMapper::map).toList();
    }
}
