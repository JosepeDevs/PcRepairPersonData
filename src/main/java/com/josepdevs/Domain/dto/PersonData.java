package com.josepdevs.Domain.dto;


import java.util.UUID;

import com.josepdevs.Domain.dto.valueobjects.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	@Id
	private UUID idPerson;

	@Column(name = "name")
	private String name;

	@Column(name = "role")
	private String role;

	@Column(name = "nid_passport")
	private String nidPassport;

	private boolean active;


	public PersonData(UUID idPerson, String name, String role, String nidPassport, boolean active ) {
		this.idPerson = idPerson;
		this.nidPassport = nidPassport;
		this.name = name;
		this.role = new Role(role).getRole();
		this.active = active;
	}

	public PersonData(UUID idPerson, String name, Role role, String nidPassport,  boolean active) {
		this.idPerson = idPerson;
		this.nidPassport = nidPassport;
		this.name = name;
		this.role = role.getRole();
		this.active = active;
	}
}
