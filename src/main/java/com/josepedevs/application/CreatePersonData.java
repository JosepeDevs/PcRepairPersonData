package com.josepedevs.application;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreatePersonData {

    private final PersonRepository personRepository;
        
	public PersonData createPerson(PersonDataDto person){
						
		PersonData newPerson = new
				PersonData(UUID.fromString(person.getId()), new NameVo(person.getName()), new NidPassportVo(person.getNidPassport()));
        return personRepository.createPersonData(newPerson);
	}	
	
}
