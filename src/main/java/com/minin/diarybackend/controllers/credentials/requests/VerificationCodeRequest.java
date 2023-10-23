package com.minin.diarybackend.controllers.credentials.requests;

import lombok.Data;

@Data
public class VerificationCodeRequest {
    private String value;
}
