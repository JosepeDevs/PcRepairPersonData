package com.josepedevs.domain.repository;

import com.josepedevs.domain.entities.PersonData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {
	
	///////////////queries/////////////
	Optional<PersonData> searchPersonData(UUID idPerson) ;
    List<PersonData> readAll();
		
	
	//////////////Commands////////////////
	boolean updatePersonData(PersonData person) ;
    void deleteHardPersonData(UUID idPerson);
    PersonData createPersonData(PersonData person);
	
}
