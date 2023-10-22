package com.minin.diarybackend.controllers.auth.requests;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
