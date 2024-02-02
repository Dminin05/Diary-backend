package com.minin.coreservice.controllers.mailing;

import com.minin.coreservice.controllers.mailing.requests.EmailSendRequest;
import com.minin.coreservice.services.mailing.IMailingService;
import com.minin.custom.CustomPrincipal;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mailing")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mailing")
public class MailingController {

    private final IMailingService mailingService;

    @PostMapping("email")
    public void sendEmailVerificationCode(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        mailingService.sendVerificationCode(principal.getEmail());
    }

    @PostMapping
    public void sendVerificationCode(@RequestBody EmailSendRequest emailSendRequest) {
        mailingService.sendVerificationCode(emailSendRequest.getEmail());
    }

}
