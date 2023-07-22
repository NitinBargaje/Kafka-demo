package com.ni3.springkafkademo.controller;

import com.ni3.springkafkademo.kafka.KafkaProducerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerExample kafkaProducerExample;

    @GetMapping(value = "produce/{msg}")
    public ResponseEntity<?> sendMessage(@PathVariable("msg") String message) {
        try {
            System.out.println("Kafka Controller:   Sending message");
            kafkaProducerExample.sendMessage(message);
            return ResponseEntity.ok("Message Sent Successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
