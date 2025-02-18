package com.challenge.customermanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.customermanagement.dto.CustomerDto;
import com.challenge.customermanagement.dto.MetricsResponse;
import com.challenge.customermanagement.entity.Customer;
import com.challenge.customermanagement.model.CustomerMetrics;
import com.challenge.customermanagement.service.CustomerMetricsService;
import com.challenge.customermanagement.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

   private final CustomerService customerService;
   private final CustomerMetricsService customerMetricsService;

   @PostMapping
   public Customer createCustomer(@RequestBody Customer customer) {
      return customerService.createCustomer(customer);
   }

   @GetMapping
   public List<CustomerDto> retrieveCustomers() {
      return customerService.retrieveCustomers()
            .stream().map(customer -> CustomerDto.builder()
                  .id(customer.getId())
                  .name(customer.getName())
                  .lastName(customer.getLastName())
                  .birthDate(customer.getBirthDate())
                  .lifeExpectancy(customerService.calculateLifeExpectancy(customer.getId()))
                  .build())
            .toList();
   }

   @GetMapping("/{id}")
   public Customer retrieveCustomerById(@PathVariable UUID id) {
      return customerService.retrieveCustomerById(id);
   }

   @GetMapping("/statistics")
   public MetricsResponse getStatistics() {
      final CustomerMetrics metrics = customerMetricsService.retrieveMetrics();
      return MetricsResponse.builder()
             .totalCustomer(metrics.totalCustomer())
             .averageAge(metrics.averageAge())
             .standardDeviation(metrics.standardDeviation())
             .build();
   }

   @GetMapping("/{id}/life-expectancy")
   public LocalDate calculateLifeExpectancy(@PathVariable UUID id) {
      return customerService.calculateLifeExpectancy(id);
   }
}
