package com.minin.diarybackend.dtos.students;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String groupName;
}
