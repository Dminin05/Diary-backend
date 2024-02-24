package com.minin.coreservice.mappers;

import com.minin.coreservice.controllers.messages.requests.MessageRequest;
import com.minin.coreservice.models.File;
import com.minin.dtos.messages.MessageDto;
import com.minin.coreservice.models.Identity;
import com.minin.coreservice.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "sender", target = "sender")
    @Mapping(source = "receiver", target = "receiver")
    @Mapping(target = "id", ignore = true)
    Message requestToEntity(MessageRequest messageRequest, Identity sender, Identity receiver);

    @Mapping(source = "message.sender.credentials.username", target = "senderUsername")
    @Mapping(source = "message.receiver.credentials.username", target = "receiverUsername")
    @Mapping(source = "files", target = "files", qualifiedByName = "filesUrl")
    MessageDto entityToDto(Message message, List<File> files);

    @Named("filesUrl")
    default List<String> studentToStudentDto(List<File> files) {
        return files.stream()
                .map(File::getUrl)
                .toList();
    }


}
