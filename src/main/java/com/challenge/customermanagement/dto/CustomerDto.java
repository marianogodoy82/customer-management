package com.challenge.customermanagement.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;

@Builder
public record CustomerDto(
      UUID id,
      String name,
      String lastName,
      LocalDate birthDate,
      LocalDate lifeExpectancy
) {

}
