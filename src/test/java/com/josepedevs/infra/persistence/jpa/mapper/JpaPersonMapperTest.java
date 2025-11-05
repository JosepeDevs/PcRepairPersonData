package com.josepedevs.infra.persistence.jpa.mapper;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;
import static org.junit.jupiter.api.Assertions.*;

import com.josepedevs.domain.entities.PersonDataDomain;
import com.josepedevs.infra.persistence.jpa.dto.PersonDataDao;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JpaPersonMapperTest {

    private JpaPersonMapper mapper;
    private final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    @BeforeEach
    void setUp() {
        mapper = new JpaPersonMapperImpl();
    }

    @Test
    void map_GivenPersonDataDao_ThenReturnsPersonDataDomain() {
        final var person = easyRandom.nextObject(PersonDataDao.class).toBuilder().nidPassport("77889966Q").build();

        final var dto = mapper.map(person);
        assertEquals(person.getName(), dto.getName());
        assertEquals(person.getMetadata(), dto.getMetadata());
        assertEquals(person.getNidPassport(), dto.getNidPassport());
        assertEquals(person.getIdUser(), dto.getIdPerson());
    }

    @Test
    void map_GivenNullPersonDataDto_ThenReturnsNull() {
        final var result = mapper.map((PersonDataDao) null);
        assertNull(result);
    }

    @Test
    void map_GivenPersonDataDomain_ThenReturnsPersonDataDao() {
        final var person = easyRandom.nextObject(PersonDataDomain.class).toBuilder()
                .nidPassport("77889955C")
                .build();
        final var dto = mapper.map(person);
        assertEquals(person.getName(), dto.getName());
        assertEquals(person.getMetadata(), dto.getMetadata());
        assertEquals(person.getNidPassport(), dto.getNidPassport());
        assertEquals(person.getIdPerson(), dto.getIdUser());
    }

    @Test
    void map_GivenNullPersonDataDomain_ThenReturnsNull() {
        final var result = mapper.map((PersonDataDomain) null);
        assertNull(result);
    }
}
