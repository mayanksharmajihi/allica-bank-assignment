package com.example.customer.exception;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void shouldBuildErrorResponse() {
        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Error message";
        String errorCode = "ERROR_CODE";
        String path = "/api/customers";

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
}
