package com.josepdevs.Domain.dto.valueobjects;

import com.josepdevs.Domain.Exceptions.PasswordNotValidException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Psswrd {
	
	private String psswrd;
	
	//in the constructor we check all we want and throw personalized exceptions as required
    public Psswrd(String psswrd) {
        if (psswrd.length() <= 5) {
            throw new PasswordNotValidException(psswrd, "Password should have more than 5 characters");
        }
        if (!psswrd.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).*")) {
            throw new PasswordNotValidException(psswrd, "Password should have at least one number, one lowercase letter, one uppercase letter, one special character and no spaces");
        }
        this.psswrd = psswrd;
    }
   

}
