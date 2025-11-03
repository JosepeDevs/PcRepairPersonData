package com.josepedevs.domain.exceptions;

import java.io.Serial;

public class NidPassportNotValidException extends MyRuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NidPassportNotValidException(String myErrorMessage, String illegalAttributeName) {
        super(myErrorMessage, illegalAttributeName, DomainErrorStatus.UNPROCESSABLE_ENTITY);
    }
}
