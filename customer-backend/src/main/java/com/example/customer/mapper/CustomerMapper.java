package com.example.customer.mapper;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setEmail(dto.getEmail());
        return customer;
    }

    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());                    // Map ID
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setDateOfBirth(customer.getDateOfBirth());
        dto.setEmail(customer.getEmail());
        return dto;
    }
}
