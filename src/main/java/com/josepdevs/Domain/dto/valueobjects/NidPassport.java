package com.josepdevs.Domain.dto.valueobjects;

import com.josepdevs.Domain.Exceptions.NidPassportNotValidException;

public class NidPassport {

	String nidPassport;
	
	public NidPassport(String nidPassport) {
		
		if ( ! nidPassport.matches("^\\d{8}[A-Z]|[A-Za-z]{2}\\d{6}|[A-Za-z]{3}\\d{6}$")) {
			throw new NidPassportNotValidException("The National Document Identifier or Passoport format was not adequate", "NID/Passport");
		}
		
		this.nidPassport = nidPassport;
	}
	
	public String getNidPassport() {
		return this.nidPassport;
	}

}
