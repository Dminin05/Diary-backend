package com.minin.coreservice.mappers;

import com.minin.dtos.TeacherDto;
import com.minin.coreservice.models.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto entityToDto(Teacher teacher);

}
