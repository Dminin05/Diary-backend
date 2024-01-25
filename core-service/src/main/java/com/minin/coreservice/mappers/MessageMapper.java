package com.minin.coreservice.mappers;

import com.minin.coreservice.controllers.messages.requests.MessageRequest;
import com.minin.dtos.MessageDto;
import com.minin.coreservice.models.Identity;
import com.minin.coreservice.models.Message;
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
