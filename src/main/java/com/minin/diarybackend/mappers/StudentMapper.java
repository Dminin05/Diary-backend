package com.minin.diarybackend.mappers;

import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groupName", source = "student.group.title")
    StudentDto entityToDto(Student student);

}
