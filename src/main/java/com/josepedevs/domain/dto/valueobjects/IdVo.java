package com.josepedevs.domain.dto.valueobjects;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public class IdVo {

    private static final String ID_SPLITTER = "-";
    private final String generatedId;

    public IdVo() {
        this.generatedId = UUID.randomUUID() + ID_SPLITTER + Instant.now().toEpochMilli();
    }
}
