package com.example.customer.service;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.exception.CustomerValidationException;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerDTO customerDTO;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john.doe@example.com");
        // Set date to a valid past date
        customerDTO.setDateOfBirth(LocalDate.now().minusYears(30));

        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setDateOfBirth(LocalDate.now().minusYears(30));
    }

    @Test
    void createCustomer_Success() {
        // Add validation expectations
        when(customerMapper.toEntity(any(CustomerDTO.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDTO(any(Customer.class))).thenReturn(customerDTO);

        CustomerDTO result = customerService.createCustomer(customerDTO);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("John");
        assertThat(result.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(result.getDateOfBirth()).isNotNull()
            .isBefore(LocalDate.now());
    }

    @Test
    void getAllCustomers_Success() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        List<CustomerDTO> result = customerService.getAllCustomers();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("John");
    }

    @Test
    void getCustomerById_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("John");
    }

    @Test
    void getCustomerById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerValidationException.class, () -> 
            customerService.getCustomerById(1L)
        );
    }
}
