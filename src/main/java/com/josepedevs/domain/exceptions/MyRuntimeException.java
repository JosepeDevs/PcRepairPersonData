package com.josepedevs.domain.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.io.Serial;

@Getter
@Log4j2
@Setter
@ToString
public class MyRuntimeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String myErrorMessage;
    private final String illegalAttributeName;
    private final DomainErrorStatus status;

    public MyRuntimeException(String myErrorMessage, String illegalAttributeName, DomainErrorStatus domainErrorStatus) {
        super(myErrorMessage);
        this.illegalAttributeName = illegalAttributeName;
        this.myErrorMessage = myErrorMessage;
        this.status = domainErrorStatus;
        log.error(myErrorMessage, illegalAttributeName);
    }

}
