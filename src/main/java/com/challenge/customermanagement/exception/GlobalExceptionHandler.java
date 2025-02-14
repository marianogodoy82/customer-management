package com.challenge.customermanagement.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

   public static final String TIMESTAMP = "timestamp";
   public static final String MESSAGE = "message";
   public static final String STATUS = "status";
   public static final String ERROR = "error";
   public static final String DETAILS = "details";

   @ExceptionHandler(CustomerNotFoundException.class)
   public ResponseEntity<Map<String, Object>> handleCustomerNotFoundException(CustomerNotFoundException ex) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put(TIMESTAMP, LocalDateTime.now());
      errorResponse.put(MESSAGE, ex.getMessage());
      errorResponse.put(STATUS, NOT_FOUND.value());
      errorResponse.put(ERROR, NOT_FOUND.getReasonPhrase());
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
      Map<String, String> errorResponse = new HashMap<>();
      errorResponse.put(TIMESTAMP, LocalDateTime.now().toString());
      errorResponse.put(MESSAGE, "The username already exists");
      errorResponse.put(STATUS, BAD_REQUEST.toString());
      errorResponse.put(ERROR, BAD_REQUEST.getReasonPhrase());
      return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put(TIMESTAMP, LocalDateTime.now());
      errorResponse.put(MESSAGE, "An unexpected error occurred");
      errorResponse.put(DETAILS, ex.getMessage());
      errorResponse.put(STATUS, INTERNAL_SERVER_ERROR.value());
      errorResponse.put(ERROR, INTERNAL_SERVER_ERROR.getReasonPhrase());
      return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
   }
}
