package com.example.diarybackend.dtos;

import lombok.Data;

@Data
public class MarkDto {
    private TeacherDto teacherDto;
    private StudentDto studentDto;
    private SubjectDto subjectDto;
    private String mark;
}
