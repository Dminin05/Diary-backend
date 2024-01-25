package com.minin.dtos.groups;

import com.minin.dtos.SubjectDto;
import com.minin.dtos.students.StudentDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GroupDto {

    private UUID id;
    private String title;
    private int year;
    private List<StudentDto> students;
    private List<SubjectDto> subjects;

}
