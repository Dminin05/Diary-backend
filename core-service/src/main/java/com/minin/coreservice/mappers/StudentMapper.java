package com.minin.coreservice.mappers;

import com.minin.dtos.students.StudentDto;
import com.minin.dtos.students.StudentDtoWithScore;
import com.minin.dtos.students.StudentProfile;
import com.minin.coreservice.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groupTitle", source = "student.group.title")
    StudentDto entityToDto(Student student);

    @Mapping(target = "score", source = "score")
    StudentDtoWithScore entityToDtoWithScore(Student student, double score);

    @Mapping(target = "groupTitle", source = "student.group.title")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "isVerified", source = "isVerified")
    StudentProfile entityToStudentProfile(Student student, String email, boolean isVerified);

}
