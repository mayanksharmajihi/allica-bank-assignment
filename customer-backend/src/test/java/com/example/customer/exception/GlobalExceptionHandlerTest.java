package com.example.customer.exception;

import com.example.customer.dto.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    void handleCustomerException() {
        CustomerException ex = new CustomerException("Customer not found", "CUSTOMER_404");
        when(webRequest.getDescription(false)).thenReturn("uri=/api/v1/customers");
        
        ResponseEntity<ErrorResponse> response = handler.handleCustomerException(ex, webRequest);
        
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ErrorResponse body = response.getBody();
        assertThat(body)
            .isNotNull()
            .satisfies(errorResponse -> {
                assertThat(errorResponse.getMessage()).isEqualTo("Customer not found");
                assertThat(errorResponse.getErrorCode()).isEqualTo("CUSTOMER_404");
                assertThat(errorResponse.getPath()).isEqualTo("uri=/api/v1/customers");
            });
    }

    @Test
    void handleCustomerValidationException() {
        CustomerValidationException ex = new CustomerValidationException("Invalid customer data");
        
        ResponseEntity<ApiResponse<Object>> response = handler.handleCustomerValidationException(ex, webRequest);
        
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ApiResponse<Object> body = response.getBody();
        assertThat(body)
            .isNotNull()
            .satisfies(apiResponse -> {
                assertThat(apiResponse.getMessage()).isEqualTo("Invalid customer data");
                assertThat(apiResponse.getStatus()).isEqualTo("400");
            });
    }

    @Test
    void handleMethodArgumentNotValidException() {
        org.springframework.validation.BeanPropertyBindingResult bindingResult = 
            new org.springframework.validation.BeanPropertyBindingResult(new Object(), "customer");
        bindingResult.addError(new FieldError("customer", "email", "Invalid email"));
        bindingResult.addError(new FieldError("customer", "name", "Name required"));
        
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(
            null, bindingResult);

        ResponseEntity<ApiResponse<Object>> response = handler.handleValidationExceptions(ex, webRequest);
        
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ApiResponse<Object> body = response.getBody();
        assertThat(body)
            .isNotNull()
            .satisfies(apiResponse -> {
                assertThat(apiResponse.getMessage())
                    .contains("email: Invalid email")
                    .contains("name: Name required");
                assertThat(apiResponse.getStatus()).isEqualTo("400");
            });
    }

    @Test
    void handleConstraintViolationException() {
        Set<ConstraintViolation<?>> violations = new HashSet<>();
        ConstraintViolationException ex = new ConstraintViolationException("Validation failed", violations);
        
        ResponseEntity<ApiResponse<Object>> response = handler.handleConstraintViolationException(ex, webRequest);
        
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ApiResponse<Object> body = response.getBody();
        assertThat(body)
            .isNotNull()
            .satisfies(apiResponse -> {
                assertThat(apiResponse.getMessage()).isEqualTo("Validation failed");
                assertThat(apiResponse.getStatus()).isEqualTo("400");
            });
    }

    @Test
    void handleGlobalException() {
        Exception ex = new RuntimeException("Unexpected error");
        
        ResponseEntity<ApiResponse<Object>> response = handler.handleGlobalException(ex, webRequest);
        
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        ApiResponse<Object> body = response.getBody();
        assertThat(body)
            .isNotNull()
            .satisfies(apiResponse -> {
                assertThat(apiResponse.getMessage()).isEqualTo("An unexpected error occurred");
                assertThat(apiResponse.getStatus()).isEqualTo("500");
            });
    }
}
