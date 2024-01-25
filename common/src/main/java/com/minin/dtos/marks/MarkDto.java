package com.minin.dtos.marks;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MarkDto {
    private String subjectName;
    private List<MarkBaseInfo> listOfMarks;
}
