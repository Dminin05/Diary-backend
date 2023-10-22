package com.minin.diarybackend.mappers;

import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.models.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject dtoToEntity(SubjectDto subjectDto);

    SubjectDto entityToDto(Subject subject);

}
