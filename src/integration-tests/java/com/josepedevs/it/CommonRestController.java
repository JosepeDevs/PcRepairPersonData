package com.josepedevs.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.HttpHeaders;

import static com.josepedevs.testutil.PreparedEasyRandom.PREPARED_EASY_RANDOM;

@Slf4j
public class CommonRestController {

    protected static HttpHeaders headers = new HttpHeaders();
    protected static final EasyRandom easyRandom = PREPARED_EASY_RANDOM;

    protected ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = System.getProperty("micro-url", "http://localhost:8080");
        headers.add("Content-Type", "application/json");
        log.debug("micro-url: {}", RestAssured.baseURI);
        System.out.println("micro-url: " + RestAssured.baseURI);
        log.debug("Input headers: {}", headers.toString());
        System.out.println("Input headers: " + headers.toString());
    }
}
