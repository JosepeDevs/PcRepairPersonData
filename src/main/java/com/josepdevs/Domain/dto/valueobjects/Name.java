package com.josepdevs.Domain.dto.valueobjects;

import com.josepdevs.Domain.Exceptions.LongInputException;

public class Name {

	String name;
	
	public Name(String name) {
		
		if( name.length()>=255 ) {
			throw new LongInputException("The name length was too much, if saved, it has been truncated.", "name");
		}
		
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
