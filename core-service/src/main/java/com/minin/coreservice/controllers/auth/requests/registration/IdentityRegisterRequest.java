package com.minin.coreservice.controllers.auth.requests.registration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StudentRegisterRequest.class, name = "STUDENT"),
        @JsonSubTypes.Type(value = TeacherRegisterRequest.class, name = "TEACHER"),
})
@Data
@AllArgsConstructor
public sealed class IdentityRegisterRequest permits StudentRegisterRequest, TeacherRegisterRequest {

    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;

}

