package com.minin.diarybackend.controllers.auth.requests.authenticate;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
