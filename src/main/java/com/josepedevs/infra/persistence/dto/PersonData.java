package com.josepedevs.infra.persistence.dto;

import com.josepedevs.domain.dto.valueobjects.MetadataVo;
import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class PersonData {

    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    @Id
    private UUID idPerson;

    @Column(name = "name")
    private String name;

    @Column(name = "nid_passport")
    private String nidPassport;

    @Lob
    @Column(name = "metadata", nullable = false)
    private String metadata;

    public PersonData(UUID idPerson, String name, String nidPassport, String metadata) {
        this.idPerson = idPerson;
        this.nidPassport = nidPassport;
        this.name = name;
        this.metadata = metadata;
    }

    public PersonData(UUID idPerson, NameVo name, NidPassportVo nidPassport, MetadataVo metadata) {
        this.idPerson = idPerson;
        this.nidPassport = nidPassport.getNidPassport();
        this.name = name.getName();
        this.metadata = metadata.getMetadata();
    }
}
