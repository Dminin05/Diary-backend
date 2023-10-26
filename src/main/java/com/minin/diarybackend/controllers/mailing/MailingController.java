package com.minin.diarybackend.controllers.mailing;

import com.minin.diarybackend.config.security.custom.CustomPrincipal;
import com.minin.diarybackend.controllers.mailing.requests.EmailSendRequest;
import com.minin.diarybackend.services.mailing.IMailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mailing")
public class MailingController {

    private final IMailingService mailingService;

    @PostMapping("email")
    public void sendEmailVerificationCode(CustomPrincipal principal) {
        mailingService.sendVerificationCode(principal.getIdentityId());
    }

    @PostMapping
    public void sendVerificationCode(@RequestBody EmailSendRequest emailSendRequest) {
        mailingService.sendVerificationCode(emailSendRequest);
    }

}
