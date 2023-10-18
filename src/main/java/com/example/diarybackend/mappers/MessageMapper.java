package com.example.diarybackend.mappers;

import com.example.diarybackend.controllers.messages.requests.MessageRequest;
import com.example.diarybackend.dtos.MessageDto;
import com.example.diarybackend.models.Identity;
import com.example.diarybackend.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "sender", target = "sender")
    @Mapping(source = "receiver", target = "receiver")
    @Mapping(target = "id", ignore = true)
    Message requestToEntity(MessageRequest messageRequest, Identity sender, Identity receiver);

    @Mapping(source = "message.sender.credentials.username", target = "senderUsername")
    @Mapping(source = "message.receiver.credentials.username", target = "receiverUsername")
    MessageDto entityToDto(Message message);

}
