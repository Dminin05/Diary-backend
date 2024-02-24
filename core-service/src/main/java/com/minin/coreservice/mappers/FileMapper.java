package com.minin.coreservice.mappers;

import com.minin.coreservice.models.File;
import com.minin.coreservice.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "message", source = "message")
    @Mapping(target = "url", source = "url")
    File dtoToEntity(Message message, String url);

}
