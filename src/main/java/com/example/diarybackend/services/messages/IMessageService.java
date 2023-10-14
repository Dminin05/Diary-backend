package com.example.diarybackend.services.messages;

import com.example.diarybackend.controllers.messages.requests.MessageRequest;
import com.example.diarybackend.dtos.MessageDto;
import com.example.diarybackend.models.Message;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    MessageDto findMessageById(UUID id);

    Message create(MessageRequest messageRequest);

    List<MessageDto> findReceivedMessages(UUID identityId, int pageIndex, int pageSize);

    List<MessageDto> findSentMessages(UUID identityId, int pageIndex, int pageSize);

}
