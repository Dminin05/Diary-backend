package com.minin.diarybackend.mappers;

import com.minin.diarybackend.dtos.TeacherDto;
import com.minin.diarybackend.models.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto entityToDto(Teacher teacher);

}
