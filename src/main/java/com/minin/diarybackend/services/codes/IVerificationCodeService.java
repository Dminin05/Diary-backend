package com.minin.diarybackend.services.codes;

import com.minin.diarybackend.models.VerificationCode;

import java.util.UUID;

public interface IVerificationCodeService {

    VerificationCode findByValue(String value);

    VerificationCode create(VerificationCode verificationCode);

    void deleteById(UUID id);

}
