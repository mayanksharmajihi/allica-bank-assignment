package com.example.customer.util;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.exception.CustomerValidationException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerValidatorTest {

    @Test
    void validateValidCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setDateOfBirth(LocalDate.now().minusYears(20));

        assertDoesNotThrow(() -> CustomerValidator.validateCustomerDTO(customerDTO));
    }

    @Test
    void validateInvalidEmail() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("invalid-email"); // Invalid email format
        customerDTO.setDateOfBirth(LocalDate.now().minusYears(20));

        CustomerValidationException exception = assertThrows(
            CustomerValidationException.class,
            () -> CustomerValidator.validateCustomerDTO(customerDTO)
        );
        assertThat(exception.getMessage()).isEqualTo("Invalid email format");
    }

    @Test
    void validateNullCustomerDTO() {
        CustomerValidationException exception = assertThrows(
            CustomerValidationException.class,
            () -> CustomerValidator.validateCustomerDTO(null)
        );
        assertThat(exception.getMessage()).isEqualTo("Customer data cannot be null");
    }

    @Test
    void validateFutureDate() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setDateOfBirth(LocalDate.now().plusDays(1));

        CustomerValidationException exception = assertThrows(
            CustomerValidationException.class,
            () -> CustomerValidator.validateCustomerDTO(customerDTO)
        );
        assertThat(exception.getMessage()).isEqualTo("Date of birth cannot be in the future");
    }

    @Test
    void validateEmptyFirstName() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setDateOfBirth(LocalDate.now().minusYears(20));

        CustomerValidationException exception = assertThrows(
            CustomerValidationException.class,
            () -> CustomerValidator.validateCustomerDTO(customerDTO)
        );
        assertThat(exception.getMessage()).isEqualTo("First name cannot be empty");
    }

    @Test
    void validateEmptyLastName() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setDateOfBirth(LocalDate.now().minusYears(20));

        CustomerValidationException exception = assertThrows(
            CustomerValidationException.class,
            () -> CustomerValidator.validateCustomerDTO(customerDTO)
        );
        assertThat(exception.getMessage()).isEqualTo("Last name cannot be empty");
    }
}
