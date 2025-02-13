package com.challenge.customermanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.customermanagement.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {


}
