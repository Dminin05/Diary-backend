package com.minin.coreservice.services.codes.results;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationCodeSuccessResult {
    private String email;
    private boolean isCorrect;
}
