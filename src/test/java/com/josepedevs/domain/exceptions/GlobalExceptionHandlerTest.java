package com.josepedevs.domain.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    void handleMyExceptions_GivenMyRuntimeException_ThenReturnsCorrectResponse() {

        var exception =
                new MyRuntimeException("Invalid attribute", "Attribute value is wrong", DomainErrorStatus.BAD_REQUEST);

        var response = handler.handleMyExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void globalExceptionManager_GivenGenericException_ThenReturnsInternalServerError() {

        var exception = new Exception("Something went wrong");

        var response = handler.globalExceptionManager(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void myNoResourceFoundException_GivenNoResourceFound_ThenReturnsNotFound() {

        var exception = new NoResourceFoundException(HttpMethod.GET, "/test");

        var response = handler.myNoResourceFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("NoResourceFoundException"));
        assertEquals(
                "The resouce you were looking for does not exist or has been removed.",
                response.getBody().get("NoResourceFoundException"));
    }

    @Test
    void jpaException_GivenJpaSystemException_ThenReturnsInternalServerError() {

        var exception = mock(JpaSystemException.class);

        var response = handler.jpaException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("PersistException"));
    }

    @Test
    void myOwnHttpMessageNotReadableException_GivenHttpMessageNotReadableException_ThenReturnsBadRequest() {

        final var message = "Unreadable JSON";
        final var exception = new HttpMessageNotReadableException(message, null, (HttpInputMessage) null);

        var response = handler.myOwnHttpMessageNotReadableException(exception);

        assertAll(
                () -> assertNotNull(exception),
                () -> assertEquals(message, exception.getMessage())
        );
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("Http Error"));
        assertTrue(response.getBody().get("Http Error").contains("HTTP message"));
    }

    @Test
    void handleIllegalArgumentException_GivenIllegalArgumentException_ThenReturnsInternalServerError() {

        var exception = new IllegalArgumentException("bad arg");

        var response = handler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void handleNullPointerException_GivenNullPointerException_ThenReturnsInternalServerError() {

        var exception = new NullPointerException("Null reference");

        var response = handler.handleNullPointerException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
