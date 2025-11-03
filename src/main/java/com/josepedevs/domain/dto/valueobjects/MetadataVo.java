package com.josepedevs.domain.dto.valueobjects;

import lombok.Getter;

@Getter
public class MetadataVo {

    private final String metadata;

    public MetadataVo(String metadata) {
        this.metadata = metadata;
    }
}
