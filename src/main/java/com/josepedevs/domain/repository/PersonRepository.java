package com.josepedevs.domain.repository;

import com.josepedevs.domain.entities.PersonDataDomain;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    // ********** queries **********//
    Optional<PersonDataDomain> searchPersonDataByIdAndDeleted(String idPerson, boolean isIncludeDeleted);

    List<PersonDataDomain> readAll();

    // ********** Commands **********//
    PersonDataDomain updatePersonData(PersonDataDomain person);

    void deleteHardPersonData(String idPerson);

    void logicalDeletePersonData(String idPerson);

    PersonDataDomain createPersonData(PersonDataDomain person);
}
