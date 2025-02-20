package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Sharma");
        customer.setEmail("john@example.com");
        customer.setDateOfBirth(LocalDate.of(1990, 1, 1));  // Add this

        Customer savedCustomer = customerRepository.save(customer);
        
        assertThat(savedCustomer.getId()).isNotNull();
        assertThat(savedCustomer.getFirstName()).isEqualTo("John");
        assertThat(savedCustomer.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void shouldFindCustomerById() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");  // Add this
        customer.setEmail("jane@example.com");
        customer.setDateOfBirth(LocalDate.of(1990, 1, 1));  // Add this
        
        Customer savedCustomer = entityManager.persistAndFlush(customer);
        
        Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getFirstName()).isEqualTo("Jane");
        assertThat(foundCustomer.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void shouldFindAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");  // Add this
        customer1.setEmail("john@example.com");
        customer1.setDateOfBirth(LocalDate.of(1990, 1, 1));  // Add this
        
        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");  // Add this
        customer2.setEmail("jane@example.com");
        customer2.setDateOfBirth(LocalDate.of(1991, 1, 1));  // Add this
        
        entityManager.persist(customer1);
        entityManager.persist(customer2);
        entityManager.flush();
        
        List<Customer> customers = customerRepository.findAll();
        
        assertThat(customers).hasSize(2);
        assertThat(customers).extracting(Customer::getFirstName)
                           .containsExactlyInAnyOrder("John", "Jane");
    }

    @Test
    void shouldDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");  // Add this
        customer.setEmail("john@example.com");
        customer.setDateOfBirth(LocalDate.of(1990, 1, 1));  // Add this
        
        Customer savedCustomer = entityManager.persistAndFlush(customer);
        customerRepository.deleteById(savedCustomer.getId());
        
        Customer foundCustomer = entityManager.find(Customer.class, savedCustomer.getId());
        assertThat(foundCustomer).isNull();
    }
}
