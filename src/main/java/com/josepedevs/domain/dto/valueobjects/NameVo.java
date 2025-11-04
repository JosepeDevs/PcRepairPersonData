package com.josepedevs.domain.dto.valueobjects;

import com.josepedevs.domain.exceptions.LongInputException;
import lombok.Getter;

@Getter
public class NameVo {

    private final String name;

    public NameVo(String name) {
        if (name.length() >= 255) {
            throw new LongInputException("The name length was too much, if saved, it has been truncated.", "name");
        }
        this.name = name;
    }
}
