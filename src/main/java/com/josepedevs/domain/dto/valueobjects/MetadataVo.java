package com.josepedevs.domain.dto.valueobjects;

import lombok.Getter;

@Getter
public class MetadataVo {

    String metadata;

    public MetadataVo(String metadata) {

        this.metadata = metadata;
    }
}
