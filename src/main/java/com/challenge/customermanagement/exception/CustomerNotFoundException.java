package com.challenge.customermanagement.exception;

public class CustomerNotFoundException extends RuntimeException {
   public CustomerNotFoundException() {
      super("Customer not found");
   }

}
