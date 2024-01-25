package com.minin.coreservice.services.messages;

import com.minin.coreservice.controllers.messages.requests.MessageRequest;
import com.minin.dtos.MessageDto;
import com.minin.coreservice.mappers.MessageMapper;
import com.minin.coreservice.models.Identity;
import com.minin.coreservice.models.Message;
import com.minin.coreservice.repositories.MessageRepository;
import com.minin.coreservice.services.identity.IIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService{

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    private final IIdentityService identityService;

    @Override
    public MessageDto findMessageById(UUID id) {
        return null;
    }

    @Override
    public MessageDto create(MessageRequest messageRequest) {

        Identity sender = identityService.findById(messageRequest.getSenderId());
        Identity receiver = identityService.findById(messageRequest.getReceiverId());

        Message message = messageMapper.requestToEntity(messageRequest, sender, receiver);
        messageRepository.save(message);

        return messageMapper.entityToDto(message);
    }

    @Override
    public List<MessageDto> findReceivedMessages(UUID identityId, int pageIndex, int pageSize) {

        Page<Message> messages = messageRepository.findMessagesByReceiverId(identityId, PageRequest.of(pageIndex, pageSize));

        return messages.stream()
                .map(messageMapper::entityToDto)
                .toList();
    }

    @Override
    public List<MessageDto> findSentMessages(UUID identityId, int pageIndex, int pageSize) {

        Page<Message> messages = messageRepository.findMessagesBySenderId(identityId, PageRequest.of(pageIndex, pageSize));

        return messages.stream()
                .map(messageMapper::entityToDto)
                .toList();
    }

}
