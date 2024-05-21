package com.josepdevs.Domain.dto;


import java.util.UUID;


import com.josepdevs.Domain.dto.valueobjects.Email;
import com.josepdevs.Domain.dto.valueobjects.Psswrd;
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
@Table(name="users")
public class Users {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	@Id
	private UUID idUser;

	@Column(name = "email")
	private String email;

	@Column(name = "psswrd")
	private String psswrd;

	@Column(name = "name")
	private String name;

	@Column(name = "role")
	private String role;

	private boolean active;

	public Users(UUID idUser, Email email, Psswrd psswrd, String name, Role role, boolean active) {
		this.idUser = idUser;
		this.email = email.getEmail();
		this.psswrd = psswrd.getPsswrd();
		this.name = name;
		this.role = role.getRole();
		this.active = active;
	}

	public Users(UUID idUser, String email, String psswrd, String name, String role, boolean active) {
		this.idUser = idUser;
		this.email = new Email(email).getEmail();
		this.psswrd = new Psswrd(psswrd).getPsswrd();
		this.name = name;
		this.role = new Role(role).getRole();
		this.active = active;
	}

}
