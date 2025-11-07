package com.josepedevs.infra.rest.mapper;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.josepedevs.domain.dto.PersonDataDto;
import com.josepedevs.domain.dto.valueobjects.NidPassportVo;
import com.josepedevs.domain.entities.PersonDataDomain;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestPersonMapperTest {

    private RestPersonMapper mapper;
    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @BeforeEach
    void setUp() {
        mapper = new RestPersonMapperImpl();
    }

    @Test
    void map_GivenPersonDataDto_ThenReturnsPersonDataDomain() {
        final var person = easyRandom.nextObject(PersonDataDto.class).toBuilder()
                .nidPassport(new NidPassportVo("77889955C"))
                .build();
        final var dto = mapper.map(person);
        assertEquals(person.getName().getName(), dto.getName());
        assertEquals(person.getMetadata().getMetadata(), dto.getMetadata());
        assertEquals(person.getNidPassport().getNidPassport(), dto.getNidPassport());
        assertEquals(person.getId(), dto.getIdPerson());
    }

    @Test
    void map_GivenNullPersonDataDto_ThenReturnsNull() {
        final var result = mapper.map((PersonDataDto) null);
        assertNull(result);
    }

    @Test
    void map_GivenPersonDataDomain_ThenReturnsRestPersonDto() {
        final var person = easyRandom.nextObject(PersonDataDomain.class).toBuilder()
                .nidPassport("77889955C")
                .build();
        final var dto = mapper.map(person);
        assertEquals(person.getName(), dto.getName());
        assertEquals(person.getMetadata(), dto.getMetadata());
        assertEquals(person.getNidPassport(), dto.getNidPassport());
        assertEquals(person.getIdPerson(), dto.getId());
    }

    @Test
    void map_GivenNullPersonDataDomain_ThenReturnsNull() {
        final var result = mapper.map((PersonDataDomain) null);
        assertNull(result);
    }
}
