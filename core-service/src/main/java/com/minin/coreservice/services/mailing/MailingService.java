package com.minin.coreservice.services.mailing;

import com.minin.coreservice.models.VerificationCode;
import com.minin.coreservice.services.codes.IVerificationCodeService;
import com.minin.dtos.mail.SendMailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailingService implements IMailingService {

    private final IVerificationCodeService verificationCodeService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendVerificationCode(String email) {

        String code = generateVerificationCode();

        try {
            SendMailDto mail = new SendMailDto(email, "Код подтверждения", code);
            kafkaTemplate.send("send-mail-topic", mail);
        } catch (KafkaException e) {
            // TODO
        }

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setValue(code);
        verificationCode.setEmail(email);

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
