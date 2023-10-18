package com.example.diarybackend.mappers;

import com.example.diarybackend.dtos.MarkDto;
import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.dtos.TeacherDto;
import com.example.diarybackend.models.Mark;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.models.Subject;
import com.example.diarybackend.models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarkMapper {

    @Mapping(source = "teacher", target = "teacher")
    @Mapping(source = "student", target = "student")
    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "mark", target = "mark")
    @Mapping(target = "id", ignore = true)
    Mark requestToEntity(String mark, Teacher teacher, Student student, Subject subject);

    MarkDto entityToDto(TeacherDto teacherDto, StudentDto studentDto, SubjectDto subjectDto, String mark);

}
