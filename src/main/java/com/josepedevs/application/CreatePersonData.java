package com.josepedevs.application;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.repository.PersonRepository;
import com.josepedevs.infra.persistence.dto.PersonData;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePersonData {

    private final PersonRepository personRepository;

    public PersonData createPerson(PersonDataDto person) {

        PersonData newPerson = PersonData.builder()
                .idPerson(UUID.fromString(person.getId()))
                .name(new NameVo(person.getName()).getName())
                .nidPassport(new NidPassportVo(person.getNidPassport()).getNidPassport())
                .metadata(new MetadataVo(person.getMetadata()).getMetadata())
                .build();
        return personRepository.createPersonData(newPerson);
    }
}
