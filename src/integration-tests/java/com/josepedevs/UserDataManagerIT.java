package com.josepedevs;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class UserDataManagerIT extends CommonRestController {

    @Test
    void getActuatorInfo_GivenValidActuatorInfoEndpoint_ThenReturnsOk() {
        RestAssured.given()
                .headers(headers)
                .when()
                .get("/actuator/info")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200);
    }
}
