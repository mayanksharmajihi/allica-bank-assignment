package com.example.customer.dto;

import com.example.customer.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void testBuilderAndGetters() {
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Test Error";
        String errorCode = "TEST_001";
        String path = "/api/test";

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(timestamp)
                .message(message)
                .errorCode(errorCode)
                .path(path)
                .build();

        assertThat(response.getTimestamp()).isEqualTo(timestamp);
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getErrorCode()).isEqualTo(errorCode);
        assertThat(response.getPath()).isEqualTo(path);
    }

    @Test
    void testSetterMethods() {
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(timestamp);
        response.setMessage("Test Message");
        response.setErrorCode("TEST_002");
        response.setPath("/api/test2");

        assertThat(response.getTimestamp()).isEqualTo(timestamp);
        assertThat(response.getMessage()).isEqualTo("Test Message");
        assertThat(response.getErrorCode()).isEqualTo("TEST_002");
        assertThat(response.getPath()).isEqualTo("/api/test2");
    }

    @Test
    void testToString() {
        ErrorResponse response = ErrorResponse.builder()
                .message("Test")
                .errorCode("CODE")
                .path("/api")
                .build();

        assertThat(response.toString())
                .contains("Test")
                .contains("CODE")
                .contains("/api");
    }
}
