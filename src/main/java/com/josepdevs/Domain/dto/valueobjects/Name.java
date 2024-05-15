package com.josepdevs.Domain.dto.valueobjects;

import com.josepdevs.Domain.Exceptions.LongInputException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Name {

	String name;
	
	public Name(String name) {
		if(name.length()>=255) {
			throw new LongInputException(name,"The name should not exceed 255 characters");
		}
		this.name=name;
	}
	
}
