package com.example.diarybackend.controllers.identity.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IdentityCreateRequest.StudentCreateRequest.class, name = "STUDENT"),
        @JsonSubTypes.Type(value = IdentityCreateRequest.TeacherCreateRequest.class, name = "TEACHER"),
})
public sealed interface IdentityCreateRequest permits IdentityCreateRequest.StudentCreateRequest, IdentityCreateRequest.TeacherCreateRequest {

    record StudentCreateRequest(
            String username,
            String password,
            String firstName,
            String lastName,
            String patronymic,
            String email
    ) implements IdentityCreateRequest {}

    record TeacherCreateRequest(
            String username,
            String password,
            String firstName,
            String lastName,
            String patronymic,
            String email
    ) implements IdentityCreateRequest {}

}

