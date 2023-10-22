package com.minin.diarybackend.controllers.auth;

import com.minin.diarybackend.controllers.auth.requests.AuthRequest;
import com.minin.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.minin.diarybackend.controllers.auth.responses.TokenResponse;
import com.minin.diarybackend.exceptions.AuthenticationException;
import com.minin.diarybackend.services.auth.IAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
    public TokenResponse refresh(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        String token = Arrays.stream(httpRequest.getCookies())
                .filter(c -> c.getName().equals("refresh-token"))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("refresh_token_is_not_present"))
                .getValue();

        TokenResponse result = authService.refreshToken(token);

        Cookie cookie = new Cookie("refresh-token", result.getRefreshToken());
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return result;
    }

}
