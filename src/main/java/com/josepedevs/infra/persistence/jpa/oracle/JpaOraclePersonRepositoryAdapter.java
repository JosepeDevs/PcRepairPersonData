package com.josepedevs.infra.persistence.jpa.oracle;

import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.MyRuntimeException;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.jpa.repository.JpaPersonRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.josepedevs.infra.persistence.jpa.mapper.JpaPersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("oracle")
@AllArgsConstructor
public class JpaOraclePersonRepositoryAdapter implements PersonRepository {

    private final JpaPersonRepository jpaPersonRepository;
    private final JpaPersonMapper jpaPersonMapper;

    // ********** COMMANDS **********//

    @Override
    public PersonData createPersonData(PersonData person) {
        jpaPersonRepository.save(jpaPersonMapper.map(person));
        // after save it contains the UUID, in the future this will return bool, and the event will publish the UUID
        return person;
    }

    @Override
    public boolean updatePersonData(PersonData person) {
        PersonData updatedPerson = jpaPersonMapper.map(jpaPersonRepository.save(jpaPersonMapper.map(person)));
        return updatedPerson.getIdPerson() != null
                && updatedPerson.getIdPerson().equals(person.getIdPerson());
    }

    @Override
    public void deleteHardPersonData(String idPerson) {
        jpaPersonRepository.deleteById(idPerson);
    }

    // ********** QUERIES **********//
    @Override
    public Optional<PersonData> searchPersonData(String idPerson) {
        return Optional.ofNullable(jpaPersonRepository.findById(idPerson)
                .map(jpaPersonMapper::map)
                .orElseThrow(() -> new MyRuntimeException("Person not found", "id", DomainErrorStatus.NOT_FOUND)));
    }

    @Override
    public List<PersonData> readAll() {
        return jpaPersonRepository.findAll().stream().map(jpaPersonMapper::map).toList();
    }
}
