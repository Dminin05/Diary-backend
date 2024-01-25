package com.minin.coreservice.mappers;

import com.minin.dtos.SubjectDto;
import com.minin.coreservice.models.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject dtoToEntity(SubjectDto subjectDto);

    SubjectDto entityToDto(Subject subject);

}
