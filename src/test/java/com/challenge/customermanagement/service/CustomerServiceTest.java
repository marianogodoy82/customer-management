package com.challenge.customermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.customermanagement.entity.Customer;
import com.challenge.customermanagement.event.CustomerEventProducer;
import com.challenge.customermanagement.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
   @Mock
   private CustomerRepository customerRepository;
   @Mock
   private CustomerEventProducer customerEventProducer;

   @InjectMocks
   private CustomerService customerService;

   private Customer customer;

   @BeforeEach
   void setUp() {
      customer = Customer
            .builder()
            .id(UUID.randomUUID())
            .name("aName")
            .lastName("aLastName")
            .birthDate(LocalDate.of(2024, Month.MAY, 20))
            .build();
   }

   @Test
   void createCustomer() {
      when(customerRepository.save(any(Customer.class))).thenReturn(customer);
      Customer newCustomer = customerService.createCustomer(customer);
      assertNotNull(newCustomer);
      assertEquals(customer.getName(), newCustomer.getName());
   }

   @Test
   void retrieveCustomers() {
      when(customerRepository.findAll()).thenReturn(List.of(customer));
      List<Customer> customers = customerService.retrieveCustomers();
      assertFalse(customers.isEmpty());
      assertEquals(1, customers.size());
   }

   @Test
   void retrieveCustomerById() {
      when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
      Customer resultado = customerService.retrieveCustomerById(customer.getId());
      assertNotNull(resultado);
      assertEquals(customer.getId(), resultado.getId());
   }
}