package com.example.diarybackend.controllers.auth.requests;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IdentityRegisterRequest.StudentCreateRequest.class, name = "STUDENT"),
        @JsonSubTypes.Type(value = IdentityRegisterRequest.TeacherCreateRequest.class, name = "TEACHER"),
})
public sealed interface IdentityRegisterRequest permits IdentityRegisterRequest.StudentCreateRequest, IdentityRegisterRequest.TeacherCreateRequest {

    record StudentCreateRequest(
            String username,
            String password,
            String firstName,
            String lastName,
            String patronymic,
            String email
    ) implements IdentityRegisterRequest {}

    record TeacherCreateRequest(
            String username,
            String password,
            String firstName,
            String lastName,
            String patronymic,
            String email
    ) implements IdentityRegisterRequest {}

}

