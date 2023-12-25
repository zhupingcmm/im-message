package com.customer.hangzhou.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test", groupId = "1")
    public void listen(String message) {
        // 处理接收到的消息
        System.out.println("Received Message: " + message);
    }
}
