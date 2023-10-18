package com.example.diarybackend.dtos.marks;

import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.dtos.TeacherDto;
import lombok.Data;

@Data
public class MarkDto {
    private TeacherDto teacherDto;
    private StudentDto studentDto;
    private SubjectDto subjectDto;
    private String mark;
}
