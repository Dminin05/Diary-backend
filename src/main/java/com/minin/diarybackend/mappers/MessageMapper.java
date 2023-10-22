package com.minin.diarybackend.mappers;

import com.minin.diarybackend.controllers.messages.requests.MessageRequest;
import com.minin.diarybackend.dtos.MessageDto;
import com.minin.diarybackend.models.Identity;
import com.minin.diarybackend.models.Message;
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
