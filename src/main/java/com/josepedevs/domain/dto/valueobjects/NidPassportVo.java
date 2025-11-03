package com.josepedevs.domain.dto.valueobjects;

import com.josepedevs.domain.exceptions.NidPassportNotValidException;
import lombok.Getter;

@Getter
public class NidPassportVo {

    private static final String PATTERN = "^(\\d{8}[A-Z]|[A-Za-z]{2}\\d{6}|[A-Za-z]{3}\\d{6})$";

    private final String nidPassport;

    public NidPassportVo(String nidPassport) {
        if (!nidPassport.matches(PATTERN)) {
            throw new NidPassportNotValidException(
                    "The National Document Identifier or Passport format was not adequate", "NID/Passport");
        }
        this.nidPassport = nidPassport;
    }

    String map(NidPassportVo value){
        return value.getNidPassport();
    }
}
