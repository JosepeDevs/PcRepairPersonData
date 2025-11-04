package com.josepedevs.infra.persistence.jpa.mapper;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.persistence.jpa.dto.PersonDataDao;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface JpaPersonMapper {

    PersonDataDomain map(PersonDataDao personDataDao);

    @Mapping(target = "deleted", constant = "false")
    PersonDataDao map(PersonDataDomain personDataDomain);
}
