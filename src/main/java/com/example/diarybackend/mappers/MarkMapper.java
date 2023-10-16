package com.example.diarybackend.mappers;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.models.Mark;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarkMapper {

    Mark requestToEntity(MarkCreateRequest markCreateRequest);

}
