package com.josepedevs.domain.exceptions;

import lombok.Getter;

import java.util.stream.Stream;

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

    public static DomainErrorStatus fromCode(int code) {
        return Stream.of(DomainErrorStatus.values())
                .filter(status -> status.getStatus() == code)
                .findFirst()
                .orElseThrow(() -> new MyRuntimeException(
                        "Unsupported " + DomainErrorStatus.class + " code: " + code));
    }
}
