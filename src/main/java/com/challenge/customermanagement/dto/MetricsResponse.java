package com.challenge.customermanagement.dto;

import lombok.Builder;

@Builder
public record MetricsResponse(
      int totalCustomer,
      double averageAge,
      double standardDeviation
) {}
