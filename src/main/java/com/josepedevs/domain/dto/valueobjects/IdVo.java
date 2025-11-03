package com.josepedevs.domain.dto.valueobjects;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class IdVo {

    private static final String ID_SPLITTER = "-";
    private final String id;

    public IdVo() {
        this.id = UUID.randomUUID() + ID_SPLITTER + Instant.now().toEpochMilli();
    }
}
