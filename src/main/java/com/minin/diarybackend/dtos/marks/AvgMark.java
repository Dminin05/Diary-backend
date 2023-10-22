package com.minin.diarybackend.dtos.marks;

import com.minin.diarybackend.dtos.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgMark {
    private StudentDto studentDto;
    private double score;
}
