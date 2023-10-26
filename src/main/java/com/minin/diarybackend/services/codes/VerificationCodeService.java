package com.minin.diarybackend.services.codes;

import com.minin.diarybackend.exceptions.BadRequestException;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.models.VerificationCode;
import com.minin.diarybackend.repositories.VerificationCodeRepository;
import com.minin.diarybackend.services.codes.results.VerificationCodeSuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public VerificationCodeSuccessResult isVerificationCodeCorrect(String value) {

        VerificationCode verificationCode = findByValue(value);

        boolean isVerificationCodeValid = verificationCode.getExpiresAt().isAfter(LocalDateTime.now());

        if (verificationCode.getValue().equals(value) && isVerificationCodeValid) {
            deleteById(verificationCode.getId());
            return new VerificationCodeSuccessResult(verificationCode.getEmail(), true);
        }

        throw new BadRequestException("wrong_verification_code");
    }

}
