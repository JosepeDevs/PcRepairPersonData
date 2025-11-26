package com.josepedevs.domain.exceptions;

import lombok.Getter;

@Getter
public enum DomainErrorStatus {
    BAD_REQUEST(400),
    UNPROCESSABLE_ENTITY(422),
    FORBIDDEN(403),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    INTERNAL_SERVER_ERROR(500);

    private final int status;

    DomainErrorStatus(final int status) {
        this.status = status;
    }

}
