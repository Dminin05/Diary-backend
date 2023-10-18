package com.example.diarybackend.mappers;

import com.example.diarybackend.dtos.TeacherDto;
import com.example.diarybackend.models.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto entityToDto(Teacher teacher);

}
