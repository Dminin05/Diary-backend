package com.example.diarybackend.controllers.auth.requests;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class StudentRegisterRequest extends IdentityRegisterRequest {

    private final UUID groupId;

    public StudentRegisterRequest(
            String username,
            String password,
            String confirmPassword,
            String firstName,
            String lastName,
            String patronymic,
            String email,
            UUID groupId
    ) {
        super(username, password, confirmPassword, firstName, lastName, patronymic, email);
        this.groupId = groupId;
    }

}
