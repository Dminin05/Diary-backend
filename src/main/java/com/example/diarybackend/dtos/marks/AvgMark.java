package com.example.diarybackend.dtos.marks;

import com.example.diarybackend.dtos.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgMark {
    private StudentDto studentDto;
    private double score;
}
