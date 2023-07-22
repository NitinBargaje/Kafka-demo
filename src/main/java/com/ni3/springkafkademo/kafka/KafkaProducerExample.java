package com.ni3.springkafkademo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerExample.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> whatsapp = this.kafkaTemplate.send("whatsapp", message);

        whatsapp.whenComplete((res, ex) -> {
            if(ex != null) {
                System.out.println("Something went wrong, error: "+ex.getMessage());
            } else {
                LOGGER.info("Message sent successfully!!!");
                System.out.println("Offset value = "+ res.getRecordMetadata().offset());
            }
        });
    }
}
