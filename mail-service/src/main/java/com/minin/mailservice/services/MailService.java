package com.minin.mailservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minin.dtos.mail.SendMailDto;
import com.minin.exceptions.ConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService implements IMailService {

    @Value("${spring.mail.sender.email}")
    private String senderEmail;

    private final JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "send-mail-topic", groupId = "1")
    @Override
    public void sendEmail(String data) {

        SendMailDto sendMailDto = new SendMailDto();
        try {
            sendMailDto = objectMapper.readValue(data, SendMailDto.class);
        } catch (JsonProcessingException e) {
            throw new ConversionException(e.getMessage());
        }

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(sendMailDto.getEmail());
        message.setSubject(sendMailDto.getTitle());
        message.setText(sendMailDto.getText());

        try {
            javaMailSender.send(message);
        } catch (MailException ex) {
            // TODO
        }

    }

}
