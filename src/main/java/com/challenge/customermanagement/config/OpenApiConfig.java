package com.challenge.customermanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
      info = @Info(
            title = "Customer Management API",
            version = "1.0.0",
            description = "API para gestionar clientes en el sistema"
      )
)
public class OpenApiConfig {

}
