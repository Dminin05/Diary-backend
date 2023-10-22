package com.minin.diarybackend.services.messages;

import com.minin.diarybackend.controllers.messages.requests.MessageRequest;
import com.minin.diarybackend.dtos.MessageDto;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    MessageDto findMessageById(UUID id);

    MessageDto create(MessageRequest messageRequest);

    List<MessageDto> findReceivedMessages(UUID identityId, int pageIndex, int pageSize);

    List<MessageDto> findSentMessages(UUID identityId, int pageIndex, int pageSize);

}
