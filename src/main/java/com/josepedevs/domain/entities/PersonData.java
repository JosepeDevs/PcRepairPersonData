package com.josepedevs.domain.entities;


import java.util.UUID;

import com.josepedevs.domain.dto.valueobjects.NameVo;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor 
@Entity 
@Table(name="persons_data")
public class PersonData {
 
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	@Id
	private UUID idPerson;

	@Column(name = "name")
	private String name;

	@Column(name = "nid_passport")
	private String nidPassport;

	public PersonData(UUID idPerson, String name, String nidPassport) {
		this.idPerson = idPerson;
		this.nidPassport = nidPassport;
		this.name = name;
	}
	
	public PersonData(UUID idPerson, NameVo name, NidPassportVo nidPassport) {
		this.idPerson = idPerson;
		this.nidPassport = nidPassport.getNidPassport();
		this.name = name.getName();
	}

}
