package com.josepedevs.domain.exceptions;

import java.io.Serial;

public class PersonNotFoundException  extends MyRuntimeException{
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public PersonNotFoundException(String myErrorMessage, String illegalAttributeName ) {
        super(myErrorMessage, illegalAttributeName );
    }
	
}
