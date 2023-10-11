package com.example.diarybackend.controllers.auth;

import com.example.diarybackend.controllers.auth.requests.AuthRequest;
import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.controllers.auth.responses.TokenResponse;
import com.example.diarybackend.services.auth.IAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody IdentityRegisterRequest identityRegisterRequest, HttpServletResponse httpResponse) {

        TokenResponse result = authService.register(identityRegisterRequest);

        Cookie cookie = new Cookie("refresh-token", result.getRefreshToken());
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return ResponseEntity.ok(result);
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest, HttpServletResponse httpResponse) throws Exception {

        TokenResponse result = authService.authenticate(authRequest);

        Cookie cookie = new Cookie("refresh-token", result.getRefreshToken());
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return ResponseEntity.ok(result);
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refresh(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        String token = Arrays.stream(httpRequest.getCookies())
                .filter(c -> c.getName().equals("refresh-token"))
                .findFirst()
                .orElseThrow() // TODO exception handler
                .getValue();

        TokenResponse result = authService.refreshToken(token);

        Cookie cookie = new Cookie("refresh-token", result.getRefreshToken());
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return ResponseEntity.ok(result);
    }

}
