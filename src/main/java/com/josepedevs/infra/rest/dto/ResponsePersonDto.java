package com.josepedevs.infra.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponsePersonDto {

    private String id;
    private String name;
    private String nidPassport;
    private String metadata;
}
