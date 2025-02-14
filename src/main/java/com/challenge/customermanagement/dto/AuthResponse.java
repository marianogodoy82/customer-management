package com.challenge.customermanagement.dto;

import lombok.Builder;

@Builder
public record AuthResponse (
      String token
) {
}
