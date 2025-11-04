package com.josepedevs.domain.entities;

import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@Data
public class PersonDataDomain {

    private String idPerson;
    private String name;
    private String nidPassport;
    private String metadata;

    public PersonDataDomain(String idPerson, String name, String nidPassport, String metadata) {
        new PersonDataDomain(idPerson, new NameVo(name), new NidPassportVo(nidPassport), new MetadataVo(metadata));
        this.idPerson = idPerson;
        this.nidPassport = nidPassport;
        this.name = name;
        this.metadata = metadata;
    }

    public PersonDataDomain(String idPerson, NameVo name, NidPassportVo nidPassport, MetadataVo metadata) {
        this.idPerson = idPerson;
        this.nidPassport = nidPassport.getNidPassport();
        this.name = name.getName();
        this.metadata = metadata.getMetadata();
    }
}
