package com.minin.dtos.students;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentProfile {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String groupTitle;
    private String email;
    private Boolean isVerified;
}
