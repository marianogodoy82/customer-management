package com.challenge.customermanagement.service;

import org.springframework.stereotype.Service;

import com.challenge.customermanagement.model.CustomerMetrics;
import com.challenge.customermanagement.repository.CustomerMetricsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerMetricsService {
   private final CustomerMetricsRepository customerMetricsRepository;

   public void updateStatistics(int age){
      final int[] newTotalCustomer = new int[1];
      final double[] newAverageAge = new double[1];
      final double[] newSumOfSquares = new double[1];
      final double[] newStandardDeviation = new double[1];

      customerMetricsRepository.findById("metrics")
            .ifPresentOrElse(metrics -> {
               newTotalCustomer[0] = metrics.totalCustomer() + 1;
               newAverageAge[0] = (metrics.averageAge() * metrics.totalCustomer() + age) / newTotalCustomer[0];
               newSumOfSquares[0] = metrics.sumOfSquares() + Math.pow(age, 2);
               newStandardDeviation[0] = Math.sqrt(newSumOfSquares[0] / newTotalCustomer[0] - Math.pow(newAverageAge[0], 2));
            }, () -> {
                  newTotalCustomer[0] = 1;
                  newAverageAge[0] = age;
                  newSumOfSquares[0] = Math.pow(age, 2);
                  newStandardDeviation[0] = Math.sqrt(newSumOfSquares[0] / newTotalCustomer[0] - Math.pow(newAverageAge[0], 2));
                  });

      customerMetricsRepository.save(CustomerMetrics.builder()
                                     .id("metrics")
                                     .totalCustomer(newTotalCustomer[0])
                                     .averageAge(newAverageAge[0])
                                     .sumOfSquares(newSumOfSquares[0])
                                     .standardDeviation(newStandardDeviation[0])
                                     .build());
   }
}
