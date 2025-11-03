package com.josepedevs.infra.rest.mapper;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonData;
import com.josepedevs.infra.rest.dto.PersonDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RestPersonMapper {

    @Mapping(target = "idPerson", ignore = true)
    PersonData map(PersonDataDto personDataDto);

    @Mapping(target = "idPerson", ignore = true)
    PersonData map(PersonDto personDto);

    @Mapping(target = "id", source = "idPerson")
    PersonDataDto map(PersonData personData);

    default NameVo mapName(String value) {
        return new NameVo(value);
    }

    default MetadataVo mapMetadata(String value) {
        return new MetadataVo(value);
    }

    default NidPassportVo mapNid(String value) {
        return new NidPassportVo(value);
    }

    default String map(NameVo name){
        return name.getName();
    }

    default String map(MetadataVo metadata){
        return metadata.getMetadata();
    }

    default String map(NidPassportVo nidPassportVo){
        return nidPassportVo.getNidPassport();
    }

}
