package com.minin.diarybackend.dtos.marks;

import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.dtos.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgMarkBySubjectDto {
    private StudentDto studentDto;
    private SubjectDto subjectDto;
    private double score;
}
