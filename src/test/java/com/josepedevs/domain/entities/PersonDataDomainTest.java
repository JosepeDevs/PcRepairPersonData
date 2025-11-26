package com.josepedevs.domain.entities;

import com.josepedevs.domain.exceptions.MyRuntimeException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonDataDomainTest {

    @ParameterizedTest
    @CsvSource({
        "LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123, 77886699Q",
        "goodName, BadNid",
        "LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123, BadNid",
    })
    void apply_GivenBadnameOrNidInBuilder_ThenThrows422(String name, String nid) {
        Throwable thrown = assertThrows(Throwable.class, () -> PersonDataDomain.builder()
                .idPerson("id")
                .name(name)
                .nidPassport(nid)
                .metadata("{}")
                .build());
        assertInstanceOf(MyRuntimeException.class, thrown);
    }

    @ParameterizedTest
    @CsvSource({
        "LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123, 77886699Q",
        "goodName, BadNid",
        "LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123LongName123, BadNid",
    })
    void apply_GivenBadnameOrNidInConstructor_ThenThrows422(String name, String nid) {
        Throwable thrown = assertThrows(Throwable.class, () -> new PersonDataDomain("id", name, nid, "{}"));
        assertInstanceOf(MyRuntimeException.class, thrown);
    }
}
