package com.example.customer.util;

import com.example.customer.dto.CustomerDTO;
import com.example.customer.exception.CustomerValidationException;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class CustomerValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void validateCustomerDTO(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new CustomerValidationException("Customer data cannot be null");
        }

        if (customerDTO.getEmail() == null || !EMAIL_PATTERN.matcher(customerDTO.getEmail()).matches()) {
            throw new CustomerValidationException("Invalid email format");
        }

        if (customerDTO.getDateOfBirth() != null && customerDTO.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new CustomerValidationException("Date of birth cannot be in the future");
        }

        // Add other validations as needed
        if (customerDTO.getFirstName() == null || customerDTO.getFirstName().trim().isEmpty()) {
            throw new CustomerValidationException("First name cannot be empty");
        }

        if (customerDTO.getLastName() == null || customerDTO.getLastName().trim().isEmpty()) {
            throw new CustomerValidationException("Last name cannot be empty");
        }
    }
}
