package com.minin.coreservice.services.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minin.coreservice.controllers.messages.requests.MessageRequest;
import com.minin.coreservice.mappers.FileMapper;
import com.minin.coreservice.models.File;
import com.minin.coreservice.repositories.FileRepository;
import com.minin.dtos.mail.SendMailDto;
import com.minin.dtos.messages.MessageDto;
import com.minin.coreservice.mappers.MessageMapper;
import com.minin.coreservice.models.Identity;
import com.minin.coreservice.models.Message;
import com.minin.coreservice.repositories.MessageRepository;
import com.minin.coreservice.services.identity.IIdentityService;
import com.minin.dtos.messages.MessageFiles;
import com.minin.exceptions.ConversionException;
import com.minin.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService{

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final ObjectMapper objectMapper;

    private final IIdentityService identityService;

    @Override
    public Message findMessageById(UUID id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("message_not_found"));
    }

    @Override
    public MessageDto create(MessageRequest messageRequest) {

        Identity sender = identityService.findById(messageRequest.getSenderId());
        Identity receiver = identityService.findById(messageRequest.getReceiverId());

        Message message = messageMapper.requestToEntity(messageRequest, sender, receiver);
        messageRepository.save(message);

        return messageMapper.entityToDto(message, new ArrayList<>());
    }

    @Override
    public List<MessageDto> findReceivedMessages(UUID identityId, int pageIndex, int pageSize) {

        Page<Message> messages = messageRepository.findMessagesByReceiverId(identityId, PageRequest.of(pageIndex, pageSize));

        return messages.stream()
                .map(message -> messageMapper.entityToDto(message, message.getFiles()))
                .toList();
    }

    @Override
    public List<MessageDto> findSentMessages(UUID identityId, int pageIndex, int pageSize) {

        Page<Message> messages = messageRepository.findMessagesBySenderId(identityId, PageRequest.of(pageIndex, pageSize));

        return messages.stream()
                .map(message -> messageMapper.entityToDto(message, message.getFiles()))
                .toList();
    }

    @Override
    @KafkaListener(topics = "upload-files", groupId = "1")
    public void addFilesToMessage(String data) {

        try {
            MessageFiles messageFiles = objectMapper.readValue(data, MessageFiles.class);

            Message message = findMessageById(messageFiles.getMessageId());

            List<File> files = messageFiles.getUrls().stream()
                    .map(url -> fileMapper.dtoToEntity(message, url))
                    .toList();

            fileRepository.saveAll(files);
        } catch (JsonProcessingException e) {
            throw new ConversionException(e.getMessage());
        }

    }

}
