package com.josepedevs.application;

import com.josepedevs.application.service.PersonFinderService;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.domain.exceptions.PersonNotFoundException;
import com.josepedevs.domain.repository.PersonRepository;
import java.util.function.UnaryOperator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("putPersonUseCase")
@AllArgsConstructor
public class PutPersonUseCaseImpl implements UnaryOperator<PersonDataDomain> {

    private PersonRepository personRepository;
    private PersonFinderService personFinder;

    @Override
    public PersonDataDomain apply(PersonDataDomain personDataDomain) {
        final var optPerson = personFinder.findByIdAndIncludeDeleted(personDataDomain.getIdPerson(), true);
        final var person = optPerson.orElseThrow(() -> new PersonNotFoundException(
                "The person with the searched id was not found", "idPerson", DomainErrorStatus.NOT_FOUND));
        final var newPerson = person.toBuilder()
                .name(new NameVo(personDataDomain.getName()).getName())
                .nidPassport(new NidPassportVo(person.getNidPassport()).getNidPassport())
                .metadata(new MetadataVo(person.getMetadata()).getMetadata())
                .build();
        return personRepository.updatePersonData(newPerson);
    }
}
