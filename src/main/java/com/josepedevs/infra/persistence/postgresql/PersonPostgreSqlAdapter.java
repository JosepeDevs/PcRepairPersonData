package com.josepedevs.infra.persistence.postgresql;

import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.PersonJpaRepository;
import com.josepedevs.infra.persistence.dto.PersonDataDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"devneon", "proaws"})
public class PersonPostgreSqlAdapter implements PersonRepository {

    private final PersonJpaRepository personJpaRepository;

    public PersonPostgreSqlAdapter(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    // ********** Commands **********//

    @Override
    public PersonDataDao createPersonData(PersonDataDao person) {
        personJpaRepository.save(person);
        // after save it should contain the UUID, in the future this will return bool, and the event will publish the
        // UUID
        return person;
    }

    @Override
    public boolean updatePersonData(PersonDataDao person) {
        PersonDataDao updatedPerson = personJpaRepository.save(person);
        return updatedPerson.getIdPerson() != null
                && updatedPerson.getIdPerson().equals(person.getIdPerson());
    }

    @Override
    public void deleteHardPersonData(UUID idPerson) {
        personJpaRepository.deleteById(idPerson);
    }

    // ********** queries **********//
    @Override
    public Optional<PersonDataDao> searchPersonData(UUID idPerson) {
        return personJpaRepository.findById(idPerson);
    }

    @Override
    public List<PersonDataDao> readAll() {
        return personJpaRepository.findAll();
    }
}
