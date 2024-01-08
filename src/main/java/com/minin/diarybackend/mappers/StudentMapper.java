package com.minin.diarybackend.mappers;

import com.minin.diarybackend.dtos.students.StudentDto;
import com.minin.diarybackend.dtos.students.StudentDtoWithScore;
import com.minin.diarybackend.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groupName", source = "student.group.title")
    StudentDto entityToDto(Student student);

    @Mapping(target = "score", source = "score")
    StudentDtoWithScore entityToDtoWithScore(Student student, double score);

}
