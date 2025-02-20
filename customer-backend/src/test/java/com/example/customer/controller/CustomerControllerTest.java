package com.example.customer.controller;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.exception.CustomerValidationException;
import com.example.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));  // Add this line
    }

    @Test
    void createCustomer_Success() throws Exception {
        when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.message").value("Customer created successfully"));
    }

    @Test
    void createCustomer_ValidationFailure() throws Exception {
        CustomerDTO invalidCustomer = new CustomerDTO();
        // Empty customer to trigger validation

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCustomer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllCustomers_Success() throws Exception {
        when(customerService.getAllCustomers())
                .thenReturn(Arrays.asList(customerDTO));

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].firstName").value("John"))
                .andExpect(jsonPath("$.message").value("Customers retrieved successfully"));
    }

    @Test
    void getAllCustomers_NoCustomersFound() throws Exception {
        when(customerService.getAllCustomers())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.message").value("No customers found"));
    }

    @Test
    void getCustomerById_Success() throws Exception {
        when(customerService.getCustomerById(1L)).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.message").value("Customer details retrieved successfully"));
    }

    @Test
    void getCustomerById_NotFound() throws Exception {
        when(customerService.getCustomerById(999L))
                .thenThrow(new CustomerValidationException("Customer not found with id: 999"));

        mockMvc.perform(get("/api/v1/customers/999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Customer not found with id: 999"));
    }
}
