package com.minin.coreservice.controllers.credentials;

import com.minin.coreservice.controllers.credentials.requests.EmailUpdateRequest;
import com.minin.coreservice.controllers.credentials.requests.VerificationCodeRequest;
import com.minin.coreservice.services.credentials.ICredentialsService;
import com.minin.custom.CustomPrincipal;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Credentials")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/credentials")
public class CredentialsController {

    private final ICredentialsService credentialsService;

    @PatchMapping
    public void updateEmail(Authentication authentication, @RequestBody EmailUpdateRequest emailUpdateRequest) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        credentialsService.updateEmail(principal.getIdentityId(), emailUpdateRequest.getValue());
    }

    @PostMapping("verify")
    public void verifyEmail(Authentication authentication, @RequestBody VerificationCodeRequest verificationCodeRequest) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        credentialsService.verifyEmail(principal.getIdentityId(), verificationCodeRequest.getValue());
    }

}
