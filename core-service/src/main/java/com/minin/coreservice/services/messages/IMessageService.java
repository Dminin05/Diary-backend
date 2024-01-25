package com.minin.coreservice.services.messages;

import com.minin.coreservice.controllers.messages.requests.MessageRequest;
import com.minin.dtos.MessageDto;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    MessageDto findMessageById(UUID id);

    MessageDto create(MessageRequest messageRequest);

    List<MessageDto> findReceivedMessages(UUID identityId, int pageIndex, int pageSize);

    List<MessageDto> findSentMessages(UUID identityId, int pageIndex, int pageSize);

}
