package com.minin.diarybackend.dtos.marks;

import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.dtos.TeacherDto;
import lombok.Data;

@Data
public class MarkDto {
    private TeacherDto teacherDto;
    private StudentDto studentDto;
    private SubjectDto subjectDto;
    private String mark;
}
