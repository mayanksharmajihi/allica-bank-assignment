package com.example.customer.exception;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerExceptionTest {

    @Test
    void shouldCreateExceptionWithMessageAndErrorCode() {
        String message = "Customer not found";
        String errorCode = "CUSTOMER_404";
        
        CustomerException exception = new CustomerException(message, errorCode);
        
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getErrorCode()).isEqualTo(errorCode);
    }
}
