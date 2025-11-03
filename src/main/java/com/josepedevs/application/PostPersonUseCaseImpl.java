package com.josepedevs.application;

import com.josepedevs.application.service.IdGeneratorService;
import com.josepedevs.domain.dto.valueobjects.IdVo;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.repository.PersonRepository;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostPersonUseCaseImpl implements Function<PersonData, PersonData> {

    private final PersonRepository personRepository;

    @Override
    public PersonData apply(PersonData person) {
        PersonData newPerson = PersonData.builder()
                .idPerson(new IdVo().getId())
                .name(new NameVo(person.getName()).getName())
                .nidPassport(new NidPassportVo(person.getNidPassport()).getNidPassport())
                .metadata(new MetadataVo(person.getMetadata()).getMetadata())
                .build();
        return personRepository.createPersonData(newPerson);
    }
}
