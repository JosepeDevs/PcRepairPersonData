package com.josepedevs.infra.rest;

import static com.josepedevs.domain.exceptions.DomainErrorStatus.UNPROCESSABLE_ENTITY;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.josepedevs.CommonRestController;
import com.josepedevs.domain.exceptions.DomainErrorStatus;
import com.josepedevs.infra.rest.dto.PersonRequestDto;
import com.josepedevs.infra.rest.dto.ResponsePersonDto;
import com.josepedevs.infra.rest.dto.RestPersonDto;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class RestPersonsControllerIT extends CommonRestController {

    private static final String ENDPOINT_BY_ID = "/persons/{id}";
    private static final String ENDPOINT_HARD = "/persons/hard/{id}";
    private static final String ENDPOINT = "persons";

    @Test
    void GetAllPersons_GivenValidRequest_ThenReturnsValidResult() throws JsonProcessingException {
        final var id = createPerson();
        try {
            var request = given().headers(headers);

            var response = request.when()
                    .get(ENDPOINT)
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();
            log.info("Response body: {}", response);

            assertThat(response).isNotNull();
            final var content = mapper.readTree(response);

            if (content.isArray()) {
                final var first = content.get(0);
                assertThat(first.toString()).contains("name", "nidPassport", "id", "metadata");

                assertThat(first.get("name")).isNotNull();
                assertThat(first.get("metadata")).isNotNull();
                assertThat(first.get("nidPassport")).isNotNull();
                assertThat(first.get("id")).isNotNull();
            } else {
                fail();
            }
        } finally {
            deleteHardPersonById(id);
        }
    }

    @Test
    void getPersonById_GivenPresentId_ThenReturnsOk() {
        final var id = createPerson();
        try {
            Response response = given().headers(headers)
                    .get(ENDPOINT_BY_ID, id)
                    .then()
                    .log()
                    .everything()
                    .statusCode(HTTP_OK)
                    .extract()
                    .response();

            final var body = response.as(RestPersonDto.class);
            assertNotNull(body);
        } finally {
            deleteHardPersonById(id);
        }
    }

    @Test
    void getPersonById_GivenNotPresentId_ThenReturnsNotFound() {
        String nonExistentId = "NOT_FOUND";
        given().headers(headers)
                .get(ENDPOINT_BY_ID, nonExistentId)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HTTP_NOT_FOUND)
                .body(containsString("not found"));
    }

    @Test
    void postPerson_GivenInvalidObject_ThenReturnsBadRequest() {

        final var invalidBody = easyRandom.nextObject(PersonRequestDto.class).toBuilder()
                .nidPassport("invalid_format")
                .build();
        given().headers(headers)
                .body(invalidBody)
                .post(ENDPOINT)
                .then()
                .log()
                .everything()
                .statusCode(UNPROCESSABLE_ENTITY.getStatus());
    }

    @Test
    void putPerson_GivenValidObject_ThenReturnsOK() {
        final var id = createPerson();
        try {
            final var body = easyRandom.nextObject(PersonRequestDto.class).toBuilder()
                    .nidPassport("77889944S")
                    .build();
            final var response = given().headers(headers)
                    .body(body)
                    .put(ENDPOINT_BY_ID, id)
                    .then()
                    .log()
                    .everything()
                    .statusCode(HTTP_OK)
                    .extract()
                    .as(ResponsePersonDto.class);

            assertNotNull(response);
            assertNotNull(response.getId());
        } finally {
            deleteHardPersonById(id);
        }
    }

    @Test
    void putPerson_GivenInvalidObject_ThenReturnsBadRequest() {

        String invalidId = "ID_TOO_^" + "LONG".repeat(256);
        final var body = easyRandom.nextObject(PersonRequestDto.class).toBuilder()
                .nidPassport("7fw3447889944S")
                .build();

        given().headers(headers)
                .body(body)
                .put(ENDPOINT_BY_ID, invalidId)
                .then()
                .log()
                .everything()
                .statusCode(DomainErrorStatus.UNPROCESSABLE_ENTITY.getStatus());
    }

    @Test
    void deleteLogicalPerson_GivenNotId_ThenReturnsNotFound() {

        var id = "NOT_FOUND";

        var request = given().headers(headers);

        request.when().delete(ENDPOINT_BY_ID, id).then().statusCode(HTTP_NOT_FOUND);
    }

    /**
     * helper method and tests deleteHard for cleanup the database
     */
    private void deleteHardPersonById(String id) {
        var request = given().headers(headers);
        request.when().delete(ENDPOINT_HARD, id).then().statusCode(anyOf(is(HTTP_NO_CONTENT), is(HTTP_NOT_FOUND)));
    }

    /**
     * helper method and tests postPersonOK
     */
    private String createPerson() {
        final var body = easyRandom.nextObject(PersonRequestDto.class).toBuilder()
                .nidPassport("77886611S")
                .build();

        final var response = given().headers(headers)
                .body(body)
                .post(ENDPOINT)
                .then()
                .log()
                .everything()
                .statusCode(HTTP_CREATED)
                .extract()
                .as(ResponsePersonDto.class);
        return response.getId();
    }
}
