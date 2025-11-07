package com.josepedevs.application;

import com.josepedevs.domain.dto.valueobjects.IdVo;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.repository.PersonRepository;
import java.util.function.UnaryOperator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("postPersonUseCase")
@AllArgsConstructor
public class PostPersonUseCaseImpl implements UnaryOperator<PersonDataDomain> {

    private final PersonRepository personRepository;

    @Override
    public PersonDataDomain apply(PersonDataDomain person) {
        PersonDataDomain newPerson = PersonDataDomain.builder()
                .idPerson(new IdVo().getGeneratedId())
                .name(new NameVo(person.getName()).getName())
                .nidPassport(new NidPassportVo(person.getNidPassport()).getNidPassport())
                .metadata(new MetadataVo(person.getMetadata()).getMetadata())
                .build();
        return personRepository.createPersonData(newPerson);
    }
}
