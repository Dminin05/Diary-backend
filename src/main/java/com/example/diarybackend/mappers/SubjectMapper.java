package com.example.diarybackend.mappers;

import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.models.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject dtoToEntity(SubjectDto subjectDto);

}
