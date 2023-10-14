package com.example.diarybackend.services.messages;

import com.example.diarybackend.controllers.messages.requests.MessageRequest;
import com.example.diarybackend.dtos.MessageDto;
import com.example.diarybackend.mappers.MessageMapper;
import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Message;
import com.example.diarybackend.repositories.MessageRepository;
import com.example.diarybackend.services.credentials.ICredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<MessageDto> findReceivedMessages(UUID identityId) {

        Credentials senderCredentials = credentialsService.findByIdentityId(identityId);
        String senderUsername = senderCredentials.getUsername();

        List<Message> messages = messageRepository.findMessagesByReceiverId(identityId);

        return messages.stream()
                .map(message -> {
                    Credentials receiverCredentials = credentialsService.findByIdentityId(message.getReceiverId());
                    String receiverUsername = receiverCredentials.getUsername();
                    return messageMapper.entityToDto(message, senderUsername,receiverUsername);
                })
                .toList();
    }

    @Override
    public List<MessageDto> findSentMessages(UUID identityId) {

        Credentials senderCredentials = credentialsService.findByIdentityId(identityId);
        String senderUsername = senderCredentials.getUsername();

        List<Message> messages = messageRepository.findMessagesBySenderId(identityId);

        return messages.stream()
                .map(message -> {
                    Credentials receiverCredentials = credentialsService.findByIdentityId(message.getReceiverId());
                    String receiverUsername = receiverCredentials.getUsername();
                    return messageMapper.entityToDto(message, senderUsername, receiverUsername);
                })
                .toList();
    }

}
