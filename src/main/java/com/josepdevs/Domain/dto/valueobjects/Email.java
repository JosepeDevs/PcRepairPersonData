package com.josepdevs.Domain.dto.valueobjects;

import com.josepdevs.Domain.Exceptions.EmailNotValidException;
import com.josepdevs.Domain.Exceptions.LongInputException;

import lombok.AllArgsConstructor;

public class Email {
	
	String email;
	
	public Email(String email) {
		
		if ( ! email.matches("^\\w[-\\.]*@([\\w-]+\\.)+[\\w-]{2,}$")) {
		    // algunos ejemplos aceptados hoola-adios@mundo.com , hola_adioos@mundo.com
			throw new EmailNotValidException(email,"The email format was not adequate, before the @ we allow the use of -_. before the @ but avoid using other characters or contact our suppor team");
		}
		
		if( email.length()>=255 ) {
			throw new LongInputException(email,"The email length was too much, if saved, it has been truncated.");
		}
		
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

}
