package com.minin.coreservice.controllers.credentials.requests;

import lombok.Data;

@Data
public class VerificationCodeRequest {
    private String value;
}
