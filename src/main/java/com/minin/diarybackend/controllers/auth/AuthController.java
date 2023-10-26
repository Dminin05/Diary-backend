package com.minin.diarybackend.controllers.auth;

import com.minin.diarybackend.config.security.custom.CustomPrincipal;
import com.minin.diarybackend.controllers.auth.requests.authenticate.AuthRequest;
import com.minin.diarybackend.controllers.auth.requests.passwords.ChangePasswordRequest;
import com.minin.diarybackend.controllers.auth.requests.passwords.RecoveryPasswordRequest;
import com.minin.diarybackend.controllers.auth.requests.registration.IdentityRegisterRequest;
import com.minin.diarybackend.controllers.auth.responses.TokenResponse;
import com.minin.diarybackend.exceptions.AuthenticationException;
import com.minin.diarybackend.services.auth.IAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("register")
    public TokenResponse register(@RequestBody IdentityRegisterRequest identityRegisterRequest, HttpServletResponse httpResponse) {

        TokenResponse result = authService.register(identityRegisterRequest);

        Cookie cookie = new Cookie("refresh-token", result.getRefreshToken());
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return result;
    }

    @PostMapping("authenticate")
    public TokenResponse authenticate(@RequestBody AuthRequest authRequest, HttpServletResponse httpResponse) throws Exception {

        TokenResponse result = authService.authenticate(authRequest);

        Cookie cookie = new Cookie("refresh-token", result.getRefreshToken());
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return result;
    }

    @PostMapping("refresh")
    public TokenResponse refresh(@CookieValue(name = "refresh-token") String token, HttpServletResponse httpResponse) {

        TokenResponse result = authService.refreshToken(token);

        Cookie cookie = new Cookie("refresh-token", result.getRefreshToken());
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return result;
    }

    @PostMapping("change-password")
    public TokenResponse changePassword(CustomPrincipal principal, @RequestBody ChangePasswordRequest changePasswordRequest) {
        return authService.changePassword(principal.getIdentityId(), changePasswordRequest);
    }

    @PostMapping("password-recovery")
    public void passwordRecovery(@CookieValue(name = "credentials_id") UUID credentialsId, @RequestBody RecoveryPasswordRequest recoveryPasswordRequest) {
        authService.passwordRecovery(credentialsId, recoveryPasswordRequest);
    }

}
