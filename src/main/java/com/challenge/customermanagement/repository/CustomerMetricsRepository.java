package com.challenge.customermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.challenge.customermanagement.model.CustomerMetrics;

@Repository
public interface CustomerMetricsRepository extends MongoRepository<CustomerMetrics, String> {

}
