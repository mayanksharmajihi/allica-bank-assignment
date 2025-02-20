package com.example.customer.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerDTOTest {

    @Test
    void testCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john@example.com");
        customerDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));

        assertThat(customerDTO.getId()).isEqualTo(1L);
        assertThat(customerDTO.getFirstName()).isEqualTo("John");
        assertThat(customerDTO.getLastName()).isEqualTo("Doe");
        assertThat(customerDTO.getEmail()).isEqualTo("john@example.com");
        assertThat(customerDTO.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 1, 1));
    }
}
