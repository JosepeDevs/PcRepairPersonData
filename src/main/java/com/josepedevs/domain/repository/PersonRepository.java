package com.josepedevs.domain.repository;

import com.josepedevs.infra.persistence.dto.PersonDataDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    // ********** queries **********//
    Optional<PersonDataDao> searchPersonData(UUID idPerson);

    List<PersonDataDao> readAll();

    // ********** Commands **********//
    boolean updatePersonData(PersonDataDao person);

    void deleteHardPersonData(UUID idPerson);

    PersonDataDao createPersonData(PersonDataDao person);
}
