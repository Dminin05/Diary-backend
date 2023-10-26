package com.minin.diarybackend.services.codes;

import com.minin.diarybackend.models.VerificationCode;
import com.minin.diarybackend.services.codes.results.VerificationCodeSuccessResult;

import java.util.UUID;

public interface IVerificationCodeService {

    VerificationCode findByValue(String value);

    VerificationCode create(VerificationCode verificationCode);

    void deleteById(UUID id);

    VerificationCodeSuccessResult isVerificationCodeCorrect(String value);

}
