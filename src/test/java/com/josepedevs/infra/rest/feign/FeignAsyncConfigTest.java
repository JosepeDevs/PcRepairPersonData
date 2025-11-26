package com.josepedevs.infra.rest.feign;

import feign.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FeignAsyncConfigTest {

    private final FeignAsyncConfig config = new FeignAsyncConfig();

    @Test
    void feignLoggerLevel_GivenConfig_ThenReturnsFullLevel() {
        final var result = config.feignLoggerLevel();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(Logger.Level.FULL, result)
        );
    }
}
