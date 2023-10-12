package com.example.diarybackend.controllers.auth.requests;

public final class TeacherRegisterRequest extends IdentityRegisterRequest {

    public TeacherRegisterRequest(
            String username,
            String password,
            String confirmPassword,
            String firstName,
            String lastName,
            String patronymic,
            String email
    ) {
        super(username, password, confirmPassword, firstName, lastName, patronymic, email);
    }

}