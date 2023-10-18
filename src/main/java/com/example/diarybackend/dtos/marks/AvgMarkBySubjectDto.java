package com.example.diarybackend.dtos.marks;

import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.dtos.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgMarkBySubjectDto {
    private StudentDto studentDto;
    private SubjectDto subjectDto;
    private double score;
}
