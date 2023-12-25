package com.customer.hangzhou.controller;

import com.customer.hangzhou.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/send/{topic}/{message}")
    public String sendMessage(@PathVariable String topic, @PathVariable String message) {
        kafkaProducer.sendMessage(topic, message);
        return "Message sent to Kafka topic '" + topic + "': " + message;
    }
}
