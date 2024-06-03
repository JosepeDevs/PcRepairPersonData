package com.josepdevs.Domain.Exceptions;

public class PersonNotFoundException  extends MyRuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//pasamos el mensaje a excepción padre y logeamos el error
	public PersonNotFoundException(String myErrorMessage, String illegalAttributeName ) {
        super(myErrorMessage, illegalAttributeName );
    }
	
}
