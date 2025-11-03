package com.josepedevs.infra.persistence.postgresql;

import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.PersonJpaRepository;
import com.josepedevs.infra.persistence.dto.PersonData;
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

	//********** Commands **********//

    @Override
    public PersonData createPersonData(PersonData person) {
        personJpaRepository.save(person);
        // after save it should contain the UUID, in the future this will return bool, and the event will publish the
        // UUID
        return person;
    }

    @Override
    public boolean updatePersonData(PersonData person) {
        PersonData updatedPerson = personJpaRepository.save(person);
        return updatedPerson.getIdPerson() != null
                && updatedPerson.getIdPerson().equals(person.getIdPerson());
    }

    @Override
    public void deleteHardPersonData(UUID idPerson) {
        personJpaRepository.deleteById(idPerson);
    }

	//********** queries **********//
    @Override
    public Optional<PersonData> searchPersonData(UUID idPerson) {
        return personJpaRepository.findById(idPerson);
    }

    @Override
    public List<PersonData> readAll() {
        return personJpaRepository.findAll();
    }
}
