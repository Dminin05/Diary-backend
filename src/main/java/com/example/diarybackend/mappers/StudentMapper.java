package com.example.diarybackend.mappers;

import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groupName", source = "student.group.title")
    StudentDto entityToDto(Student student);

}
