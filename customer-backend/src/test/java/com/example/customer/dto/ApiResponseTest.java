package com.example.customer.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void testSuccessResponse() {
        String data = "Test Data";
        String message = "Success Message";
        
        ApiResponse<String> response = ApiResponse.success(data, message);
        
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getStatus()).isEqualTo("200");
    }

    @Test
    void testErrorResponse() {
        String message = "Error Message";
        String status = "400";
        
        ApiResponse<Object> response = ApiResponse.error(message, status);
        
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getStatus()).isEqualTo(status);
    }

    @Test
    void testAllArgsConstructor() {
        ApiResponse<String> response = new ApiResponse<>(true, "message", "200", "data");
        
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("message");
        assertThat(response.getStatus()).isEqualTo("200");
        assertThat(response.getData()).isEqualTo("data");
    }

    @Test
    void testNoArgsConstructor() {
        ApiResponse<Object> response = new ApiResponse<>();
        
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isNull();
        assertThat(response.getStatus()).isNull();
        assertThat(response.getData()).isNull();
    }

    @Test
    void testEqualsAndHashCode() {
        ApiResponse<String> response1 = new ApiResponse<>(true, "msg", "200", "data");
        ApiResponse<String> response2 = new ApiResponse<>(true, "msg", "200", "data");
        ApiResponse<String> response3 = new ApiResponse<>(false, "error", "400", null);

        assertThat(response1).isEqualTo(response2);
        assertThat(response1).isNotEqualTo(response3);
        assertThat(response1).isNotEqualTo(null);
        assertThat(response1).isNotEqualTo(new Object());
        assertThat(response1.hashCode()).isEqualTo(response2.hashCode());
    }

    @Test
    void testToString() {
        ApiResponse<String> response = new ApiResponse<>(true, "msg", "200", "data");
        String toString = response.toString();

        assertThat(toString)
            .contains("success=true")
            .contains("message='msg'")
            .contains("status='200'")
            .contains("data=data");
    }

    @Test
    void testSetterMethods() {
        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("test message");
        response.setStatus("201");
        response.setData("test data");

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("test message");
        assertThat(response.getStatus()).isEqualTo("201");
        assertThat(response.getData()).isEqualTo("test data");
    }
}
