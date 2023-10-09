package com.example.diarybackend.controllers.auth;

import com.example.diarybackend.controllers.auth.requests.AuthRequest;
import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.controllers.auth.responses.AuthResponse;
import com.example.diarybackend.exceptions.AppError;
import com.example.diarybackend.services.auth.IAuthService;
import com.example.diarybackend.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final IAuthService authService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody IdentityRegisterRequest identityRegisterRequest) {
        return ResponseEntity.ok(authService.register(identityRegisterRequest));
    }

    @PostMapping("authorize")
    public ResponseEntity<?> authorize(@RequestBody AuthRequest authRequest) {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        } catch (BadCredentialsException e) {

            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.UNAUTHORIZED.value(),
                            "wrong_login_or_password"
                    ),
                    HttpStatus.UNAUTHORIZED
            );

        }

        UserDetails userDetails = authService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateAccessToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
