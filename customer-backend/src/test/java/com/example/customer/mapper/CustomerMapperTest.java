package com.example.customer.mapper;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.model.Customer;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    private final CustomerMapper mapper = new CustomerMapper();

    @Test
    void shouldMapToDTO() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setDateOfBirth(java.time.LocalDate.of(1990, 1, 1));

        CustomerDTO dto = mapper.toDTO(customer);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(customer.getId());
        assertThat(dto.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(customer.getLastName());
        assertThat(dto.getEmail()).isEqualTo(customer.getEmail());
        assertThat(dto.getDateOfBirth()).isEqualTo(customer.getDateOfBirth());
    }

    @Test
    void shouldMapToEntity() {
        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john@example.com");
        dto.setDateOfBirth(java.time.LocalDate.of(1990, 1, 1));

        Customer customer = mapper.toEntity(dto);

        assertThat(customer).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(customer.getLastName()).isEqualTo(dto.getLastName());
        assertThat(customer.getEmail()).isEqualTo(dto.getEmail());
        assertThat(customer.getDateOfBirth()).isEqualTo(dto.getDateOfBirth());
    }

    @Test
    void shouldMapToEntityForCreation() {
        CustomerDTO dto = new CustomerDTO();
        // Don't set ID for creation
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john@example.com");
        dto.setDateOfBirth(java.time.LocalDate.of(1990, 1, 1));

        Customer customer = mapper.toEntity(dto);

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isNull(); // ID should be null for new entities
        assertThat(customer.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(customer.getLastName()).isEqualTo(dto.getLastName());
        assertThat(customer.getEmail()).isEqualTo(dto.getEmail());
        assertThat(customer.getDateOfBirth()).isEqualTo(dto.getDateOfBirth());
    }

    @Test
    void shouldHandleNullValues() {
        assertThat(mapper.toDTO(null)).isNull();
        assertThat(mapper.toEntity(null)).isNull();
    }
}
