package com.minin.diarybackend.services.mailing;

import com.minin.diarybackend.models.VerificationCode;
import com.minin.diarybackend.services.codes.IVerificationCodeService;
import com.minin.diarybackend.services.credentials.ICredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailingService implements IMailingService {

    private final JavaMailSender javaMailSender;

    private final ICredentialsService credentialsService;
    private final IVerificationCodeService verificationCodeService;

    @Value("${spring.mail.sender.email}")
    private String senderEmail;

    @Override
    public void sendEmailVerificationCode(UUID identityId) {

        String email = credentialsService.findByIdentityId(identityId).getEmail();
        String code = generateVerificationCode();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(email);
        message.setSubject("Код подтверждения");
        message.setText(code);

        try {
            javaMailSender.send(message);
        } catch (MailException ex) {
            // TODO
        }

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setValue(code);

        verificationCodeService.create(verificationCode);
    }

    private String generateVerificationCode() {

        String charSet = "0123456789";

        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {

            int index = (int)(charSet.length() * Math.random());

            sb.append(charSet.charAt(index));
        }

        return sb.toString();
    }

}
