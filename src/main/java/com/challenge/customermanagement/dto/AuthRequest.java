package com.challenge.customermanagement.dto;

import lombok.Builder;

@Builder
public record AuthRequest(
      String username,
      String password
) {}
