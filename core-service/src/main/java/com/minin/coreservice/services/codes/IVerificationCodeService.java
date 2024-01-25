package com.minin.coreservice.services.codes;


import com.minin.coreservice.models.VerificationCode;
import com.minin.coreservice.services.codes.results.VerificationCodeSuccessResult;

import java.util.UUID;

public interface IVerificationCodeService {

    VerificationCode findByValue(String value);

    VerificationCode create(VerificationCode verificationCode);

    void deleteById(UUID id);

    VerificationCodeSuccessResult isVerificationCodeCorrect(String value);

}
