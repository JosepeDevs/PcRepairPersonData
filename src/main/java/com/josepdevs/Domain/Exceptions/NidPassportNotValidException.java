package com.josepdevs.Domain.Exceptions;

public class NidPassportNotValidException extends MyRuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//pasamos el mensaje a excepción padre y logeamos el error
	public NidPassportNotValidException(String myErrorMessage, String illegalAttributeName ) {
        super(myErrorMessage, illegalAttributeName);
    }
	
}
