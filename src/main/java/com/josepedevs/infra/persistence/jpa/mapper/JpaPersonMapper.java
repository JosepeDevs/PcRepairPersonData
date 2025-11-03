package com.josepedevs.infra.persistence.jpa.mapper;

import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.infra.persistence.jpa.dto.PersonDataDao;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface JpaPersonMapper {

    PersonData map(PersonDataDao personDataDao);

    PersonDataDao map(PersonData personData);

}
