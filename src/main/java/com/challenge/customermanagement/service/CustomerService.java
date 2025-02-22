package com.challenge.customermanagement.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.challenge.customermanagement.entity.Customer;
import com.challenge.customermanagement.event.CustomerEventProducer;
import com.challenge.customermanagement.exception.CustomerNotFoundException;
import com.challenge.customermanagement.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

   private final CustomerRepository customerRepository;
   private final CustomerEventProducer customerEventProducer;

   public Customer createCustomer(Customer customer) {
      final Customer result = customerRepository.save(customer);
      customerEventProducer.sendEvent(STR."\{calculateAge(customer.getBirthDate())}");
      return result;

   }
   public List<Customer> retrieveCustomers() {
      return customerRepository.findAll();
   }

   public Customer retrieveCustomerById(UUID id) {
      return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
   }

   private long calculateAge(LocalDate birthDate) {
      return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
   }

   public LocalDate calculateLifeExpectancy(UUID id) {
      int averageLifeExpectancy = 75;
      return customerRepository
            .findById(id)
            .map(customer -> customer.getBirthDate().plusYears(averageLifeExpectancy - calculateAge(customer.getBirthDate())))
            .orElseThrow(CustomerNotFoundException::new);
   }
}
