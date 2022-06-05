package com.demo.chatting.controller;

import com.demo.chatting.constants.KafkaConstants;
import com.demo.chatting.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping(value = "/api/message")
    public void sendMessage(@RequestBody Message message) {
        log.info("Send Message : " + message.toString());
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            // Sending the message to Kafka Topic Queue
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/sendMessage")
    @SendTo("/optic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        // Sending this message to all the subscribers
        return message;
    }
}