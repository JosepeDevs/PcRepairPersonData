package com.josepedevs.infra.persistence.dto;

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
public class PersonDataDao {

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

    public PersonDataDao(UUID idPerson, String name, String nidPassport, String metadata) {
        this.idPerson = idPerson;
        this.nidPassport = nidPassport;
        this.name = name;
        this.metadata = metadata;
    }
}
