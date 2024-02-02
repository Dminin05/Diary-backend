package com.minin.mailservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;

public interface IMailService {

    @KafkaListener(topics = "send-mail-topic", groupId = "1")
    void sendEmail(String data) throws JsonProcessingException;
}
