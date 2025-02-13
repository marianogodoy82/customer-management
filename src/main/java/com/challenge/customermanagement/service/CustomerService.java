package com.challenge.customermanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.challenge.customermanagement.entity.Customer;
import com.challenge.customermanagement.exception.CustomerNotFoundException;
import com.challenge.customermanagement.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

   private final CustomerRepository customerRepository;

   public Customer createCustomer(Customer customer) {
      return customerRepository.save(customer);
   }
   public List<Customer> retrieveCustomers() {
      return customerRepository.findAll();
   }

   public Customer retrieveCustomerById(UUID id) {
      return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
   }
}
