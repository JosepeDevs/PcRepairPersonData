package com.josepedevs.infra.rest.mapper;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.rest.dto.RestPersonDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RestPersonMapper {

    @Mapping(target = "idPerson", source = "id")
    @Mapping(target = "nidPassport", source = "nidPassport", qualifiedByName = "mapToStringNid")
    @Mapping(target = "metadata", source = "metadata", qualifiedByName = "mapToStringMetadata")
    @Mapping(target = "name", source = "name", qualifiedByName = "mapToStringName")
    PersonDataDomain map(PersonDataDto personDataDto);

    @Mapping(target = "id", source = "idPerson")
    RestPersonDto map(PersonDataDomain restPersonDto);

    @Named("mapName")
    default NameVo mapName(String value) {
        return new NameVo(value);
    }

    @Named("mapMetadata")
    default MetadataVo mapMetadata(String value) {
        return new MetadataVo(value);
    }

    @Named("mapNid")
    default NidPassportVo mapNid(String value) {
        return new NidPassportVo(value);
    }

    @Named("mapToStringName")
    default String map(NameVo name) {
        return name.getName();
    }

    @Named("mapToStringMetadata")
    default String map(MetadataVo metadata) {
        return metadata.getMetadata();
    }

    @Named("mapToStringNid")
    default String map(NidPassportVo nidPassportVo) {
        return nidPassportVo.getNidPassport();
    }
}
