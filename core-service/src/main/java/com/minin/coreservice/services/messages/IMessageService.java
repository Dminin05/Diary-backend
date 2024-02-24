package com.minin.coreservice.services.messages;

import com.minin.coreservice.controllers.messages.requests.MessageRequest;
import com.minin.coreservice.models.Message;
import com.minin.dtos.messages.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    Message findMessageById(UUID id);

    MessageDto create(MessageRequest messageRequest);

    List<MessageDto> findReceivedMessages(UUID identityId, int pageIndex, int pageSize);

    List<MessageDto> findSentMessages(UUID identityId, int pageIndex, int pageSize);

    @KafkaListener(topics = "upload-files", groupId = "1")
    void addFilesToMessage(String data);

}
