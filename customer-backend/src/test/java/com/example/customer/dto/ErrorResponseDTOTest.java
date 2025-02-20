package com.example.customer.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseDTOTest {

    @Test
    void testNoArgsConstructor() {
        ErrorResponse errorResponse = new ErrorResponse();
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getMessage()).isNull();
        assertThat(errorResponse.getErrorCode()).isNull();
        assertThat(errorResponse.getPath()).isNull();
        assertThat(errorResponse.getTimestamp()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        String message = "Test error";
        String errorCode = "404";
        String path = "/api/test";
        
        ErrorResponse errorResponse = new ErrorResponse(message, errorCode, path);

        assertThat(errorResponse.getMessage()).isEqualTo(message);
        assertThat(errorResponse.getErrorCode()).isEqualTo(errorCode);
        assertThat(errorResponse.getPath()).isEqualTo(path);
        assertThat(errorResponse.getTimestamp()).isNotNull();
    }

    @Test
    void testSettersAndGetters() {
        ErrorResponse errorResponse = new ErrorResponse();
        LocalDateTime now = LocalDateTime.now();
        
        errorResponse.setTimestamp(now);
        errorResponse.setMessage("Test message");
        errorResponse.setErrorCode("500");
        errorResponse.setPath("/api/path");

        assertThat(errorResponse.getTimestamp()).isEqualTo(now);
        assertThat(errorResponse.getMessage()).isEqualTo("Test message");
        assertThat(errorResponse.getErrorCode()).isEqualTo("500");
        assertThat(errorResponse.getPath()).isEqualTo("/api/path");
    }
}
