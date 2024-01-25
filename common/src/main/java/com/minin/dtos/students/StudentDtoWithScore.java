package com.minin.dtos.students;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDtoWithScore {
    private String firstName;
    private String lastName;
    private String patronymic;
    private double score;
}