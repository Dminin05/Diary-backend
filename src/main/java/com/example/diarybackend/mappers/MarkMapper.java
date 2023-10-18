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
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarkMapper {

    @Mapping(source = "teacher", target = "teacher")
    @Mapping(source = "student", target = "student")
    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "mark", target = "mark")
    @Mapping(target = "id", ignore = true)
    Mark requestToEntity(String mark, Teacher teacher, Student student, Subject subject);

    @Mapping(source = "teacher", target = "teacherDto", qualifiedByName = "teacherDto")
    @Mapping(source = "student", target = "studentDto", qualifiedByName = "studentDto")
    @Mapping(source = "subject", target = "subjectDto", qualifiedByName = "subjectDto")
    MarkDto entityToDto(Mark mark);

    @Named("studentDto")
    default StudentDto studentToStudentDto(Student student) {
        return Mappers.getMapper(StudentMapper.class).entityToDto(student);
    }

    @Named("teacherDto")
    default TeacherDto teacherToTeacherDto(Teacher teacher) {
        return Mappers.getMapper(TeacherMapper.class).entityToDto(teacher);
    }

    @Named("subjectDto")
    default SubjectDto subjectToSubjectDto(Subject subject) {
        return Mappers.getMapper(SubjectMapper.class).entityToDto(subject);
    }

}
