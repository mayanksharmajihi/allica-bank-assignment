package com.example.customer.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    void testCustomerEntity() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setDateOfBirth(LocalDate.of(1990, 1, 1));

        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getFirstName()).isEqualTo("John");
        assertThat(customer.getLastName()).isEqualTo("Doe");
        assertThat(customer.getEmail()).isEqualTo("john@example.com");
        assertThat(customer.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 1, 1));
    }
}
