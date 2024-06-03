package com.josepdevs.Domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.josepdevs.Domain.dto.PersonDataDto;
import com.josepdevs.Domain.dto.PersonData;

public interface PersonRepository {
	
	///////////////queries/////////////
	public Optional<PersonData> searchPersonData(UUID idPerson) ;
    public List<PersonData> readAll();
		
	
	//////////////Commands////////////////
	public boolean updatePersonData(PersonData person) ;
    public void deleteHardPersonData(UUID idPerson);
    public PersonData createPersonData(PersonData person);
	
}
