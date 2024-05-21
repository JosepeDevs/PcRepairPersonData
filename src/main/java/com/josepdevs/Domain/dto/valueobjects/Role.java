package com.josepdevs.Domain.dto.valueobjects;

import com.josepdevs.Domain.Exceptions.RoleNotValidException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {

	String role;
	
	public Role(String role) {
		
		switch (role) {
	    case "admin": {
	        break;
	    }
	    case "user": {
	        break;
	    }
	    default:
	    	throw new RoleNotValidException("Role should be one of the available options ('user' or 'admin')", role);
		}
		
		this.role=role;
	}
}
