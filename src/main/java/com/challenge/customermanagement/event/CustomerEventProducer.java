package com.challenge.customermanagement.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerEventProducer {
   private final KafkaTemplate<String, String> kafkaTemplate;

   public void sendEvent(String message){
      kafkaTemplate.send("customer-topic", message);
   }
}
