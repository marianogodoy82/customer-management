package com.challenge.customermanagement.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(CustomerNotFoundException.class)
   public ResponseEntity<Map<String, Object>> handleCustomerNotFoundException(CustomerNotFoundException ex) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("timestamp", LocalDateTime.now());
      errorResponse.put("message", ex.getMessage());
      errorResponse.put("status", NOT_FOUND.value());
      errorResponse.put("error", NOT_FOUND.getReasonPhrase());
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("timestamp", LocalDateTime.now());
      errorResponse.put("message", "An unexpected error occurred");
      errorResponse.put("details", ex.getMessage());
      errorResponse.put("status", INTERNAL_SERVER_ERROR.value());
      errorResponse.put("error", INTERNAL_SERVER_ERROR.getReasonPhrase());
      return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
   }
}
