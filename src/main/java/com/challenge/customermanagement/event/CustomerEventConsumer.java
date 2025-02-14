package com.challenge.customermanagement.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.challenge.customermanagement.service.CustomerMetricsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerEventConsumer {
   private final CustomerMetricsService customerMetricsService;

   @KafkaListener(topics = "customer-topic", groupId = "management-customer-group")
   public void consumeEvent(String message){
      log.info("Event received: {}", message);

      int age = Integer.parseInt(message);

      customerMetricsService.updateStatistics(age);
   }
}
