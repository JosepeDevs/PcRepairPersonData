package com.josepedevs.infra.persistence.jpa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class PersonDataDao {

    @Column(name = "id_user")
    @Id
    private String idPerson;

    @Column(name = "name")
    private String name;

    @Column(name = "nid_passport")
    private String nidPassport;

    @Lob
    @Column(name = "metadata", nullable = false)
    private String metadata;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;
}
