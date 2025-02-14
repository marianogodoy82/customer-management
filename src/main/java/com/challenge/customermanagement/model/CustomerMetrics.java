package com.challenge.customermanagement.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Builder;

@Document(collection = "customerMetrics")
@Builder
public record CustomerMetrics(
      @Id
      String id,
      int totalCustomer,
      double averageAge,
      double sumOfSquares,
      double standardDeviation
){ }
