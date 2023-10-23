package com.minin.diarybackend.services.codes;

import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.models.VerificationCode;
import com.minin.diarybackend.repositories.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationCodeService implements IVerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public VerificationCode findByValue(String value) {
        return verificationCodeRepository.findByValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("verification_code_not_found"));
    }

    @Override
    public VerificationCode create(VerificationCode verificationCode) {
        return verificationCodeRepository.save(verificationCode);
    }

    @Override
    public void deleteById(UUID id) {
        verificationCodeRepository.deleteById(id);
    }

}
