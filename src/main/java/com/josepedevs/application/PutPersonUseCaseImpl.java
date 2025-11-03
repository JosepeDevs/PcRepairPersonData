package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;

import java.util.function.Consumer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PutPersonUseCaseImpl implements Consumer<PersonData> {

    private PersonRepository personRepository;
    private PersonFinderService personFinder;

    @Override
    public void accept(PersonData personData) {
        final var optPerson = personFinder.findById(personData.getIdPerson());
        final var person = optPerson.orElseThrow(
                () -> new PersonNotFoundException("The person with the searched id was not found", "idPerson", DomainErrorStatus.NOT_FOUND));
        person.setName(new NameVo(personData.getName()).getName());
        person.setNidPassport(new NidPassportVo(person.getNidPassport()).getNidPassport());
        person.setMetadata(new MetadataVo(person.getMetadata()).getMetadata());
        personRepository.updatePersonData(person);
    }
}
