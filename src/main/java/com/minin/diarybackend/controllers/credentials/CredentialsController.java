package com.minin.diarybackend.controllers.credentials;

import com.minin.diarybackend.config.security.custom.CustomPrincipal;
import com.minin.diarybackend.controllers.credentials.requests.EmailUpdateRequest;
import com.minin.diarybackend.services.credentials.ICredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/credentials")
public class CredentialsController {

    private final ICredentialsService credentialsService;

    @PatchMapping
    public void updateEmail(CustomPrincipal principal, @RequestBody EmailUpdateRequest emailUpdateRequest) {
        credentialsService.updateEmail(principal.getIdentityId(), emailUpdateRequest.getValue());
    }

}
