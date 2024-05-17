package com.josepdevs.Domain.dto;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.josepdevs.Domain.dto.valueobjects.Email;
import com.josepdevs.Domain.dto.valueobjects.Name;
import com.josepdevs.Domain.dto.valueobjects.Psswrd;
import com.josepdevs.Domain.dto.valueobjects.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private UUID idUser;
	
	private Email email;
	private Psswrd psswrd;
	private Name name;
	private Role role;
	private boolean active;
	
	
	public UserEntity(UUID idUser, Email email, Psswrd psswrd, Name name, Role role, boolean active) {
		this.idUser = idUser;
		this.email= email;
		this.psswrd = psswrd;
		this.name = name;
		this.role = role;
		this.active = active;
	}
	
	
	public UserEntity(UUID idUser, String email, String psswrd, String name, String role, boolean active) {
		this.idUser = idUser;
		this.email= new Email(email);
		this.psswrd = new Psswrd(psswrd);
		this.name = new Name(name);
		this.role = new Role(role);
		this.active = active;
	}
	

}
