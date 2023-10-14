package com.example.diarybackend.mappers;

import com.example.diarybackend.controllers.messages.requests.MessageRequest;
import com.example.diarybackend.dtos.MessageDto;
import com.example.diarybackend.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    Message requestToEntity(MessageRequest messageRequest);

    @Mapping(source = "senderUsername", target = "senderUsername")
    @Mapping(source = "receiverUsername", target = "receiverUsername")
    MessageDto entityToDto(Message message, String senderUsername, String receiverUsername);

}
