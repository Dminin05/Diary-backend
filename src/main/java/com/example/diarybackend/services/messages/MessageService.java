package com.example.diarybackend.services.messages;

import com.example.diarybackend.controllers.messages.requests.MessageRequest;
import com.example.diarybackend.dtos.MessageDto;
import com.example.diarybackend.mappers.MessageMapper;
import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Message;
import com.example.diarybackend.repositories.MessageRepository;
import com.example.diarybackend.services.credentials.ICredentialsService;
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

    private final ICredentialsService credentialsService;

    @Override
    public MessageDto findMessageById(UUID id) {
        return null;
    }

    @Override
    public Message create(MessageRequest messageRequest) {

        Message message = messageMapper.requestToEntity(messageRequest);
        return messageRepository.save(message);
    }

    @Override
    public List<MessageDto> findReceivedMessages(UUID identityId, int pageIndex, int pageSize) {

        Credentials senderCredentials = credentialsService.findByIdentityId(identityId);
        String senderUsername = senderCredentials.getUsername();

        Page<Message> messages = messageRepository.findMessagesByReceiverId(identityId, PageRequest.of(pageIndex, pageSize));

        return messages.stream()
                .map(message -> {
                    Credentials receiverCredentials = credentialsService.findByIdentityId(message.getReceiverId());
                    String receiverUsername = receiverCredentials.getUsername();
                    return messageMapper.entityToDto(message, senderUsername,receiverUsername);
                })
                .toList();
    }

    @Override
    public List<MessageDto> findSentMessages(UUID identityId, int pageIndex, int pageSize) {

        Credentials senderCredentials = credentialsService.findByIdentityId(identityId);
        String senderUsername = senderCredentials.getUsername();

        Page<Message> messages = messageRepository.findMessagesBySenderId(identityId, PageRequest.of(pageIndex, pageSize));

        return messages.stream()
                .map(message -> {
                    Credentials receiverCredentials = credentialsService.findByIdentityId(message.getReceiverId());
                    String receiverUsername = receiverCredentials.getUsername();
                    return messageMapper.entityToDto(message, senderUsername, receiverUsername);
                })
                .toList();
    }

}
