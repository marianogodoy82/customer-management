package com.challenge.customermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.customermanagement.model.CustomerMetrics;
import com.challenge.customermanagement.repository.CustomerMetricsRepository;

@ExtendWith(MockitoExtension.class)
class CustomerMetricsServiceTest {
   @Mock
   private CustomerMetricsRepository customerMetricsRepository;
   @InjectMocks
   private CustomerMetricsService customerMetricsService;

   private CustomerMetrics customerMetrics;
   @BeforeEach
   void setUp() {
      customerMetrics = CustomerMetrics
            .builder()
            .id("anId")
            .totalCustomer(1)
            .averageAge(20)
            .sumOfSquares(400)
            .standardDeviation(0)
            .build();
   }
   @Test
   void updateStatistics() {
      Mockito.when(customerMetricsRepository.findById(anyString())).thenReturn(Optional.of(customerMetrics));
      customerMetricsService.updateStatistics(20);
      Mockito.verify(customerMetricsRepository, Mockito.atLeastOnce()).save(any(CustomerMetrics.class));
   }

   @Test
   void updateStatisticsFirstTime() {
      Mockito.when(customerMetricsRepository.findById(anyString())).thenReturn(Optional.empty());
      customerMetricsService.updateStatistics(20);
      Mockito.verify(customerMetricsRepository, Mockito.atLeastOnce()).save(any(CustomerMetrics.class));
   }

   @Test
   void retrieveMetrics() {
      Mockito.when(customerMetricsRepository.findById(anyString())).thenReturn(Optional.of(customerMetrics));
      final CustomerMetrics metricsRetrieved = customerMetricsService.retrieveMetrics();
      assertNotNull(metricsRetrieved);
      assertEquals(customerMetrics.id(), metricsRetrieved.id());
   }

   @Test
   void retrieveNonexistentMetrics() {
      Mockito.when(customerMetricsRepository.findById(anyString())).thenReturn(Optional.empty());
      assertThrows(RuntimeException.class, () -> customerMetricsService.retrieveMetrics());

   }
}