package com.minin.diarybackend.controllers.codes;

import com.minin.diarybackend.controllers.credentials.requests.VerificationCodeRequest;
import com.minin.diarybackend.models.Credentials;
import com.minin.diarybackend.services.codes.IVerificationCodeService;
import com.minin.diarybackend.services.codes.results.VerificationCodeSuccessResult;
import com.minin.diarybackend.services.credentials.ICredentialsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Verification codes")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/verification")
public class VerificationCodeController {

    private final IVerificationCodeService verificationCodeService;
    private final ICredentialsService credentialsService;

    @PostMapping("verify-recovering")
    public void verifyRecovering(@RequestBody VerificationCodeRequest verificationCodeRequest, HttpServletResponse httpResponse) {

        VerificationCodeSuccessResult result = verificationCodeService.isVerificationCodeCorrect(verificationCodeRequest.getValue());
        Credentials credentials = credentialsService.findByEmail(result.getEmail());

        Cookie cookie = new Cookie("credentials_id", credentials.getId().toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/diary/api/v1/auth/password-recovery");
        httpResponse.addCookie(cookie);
    }

}
