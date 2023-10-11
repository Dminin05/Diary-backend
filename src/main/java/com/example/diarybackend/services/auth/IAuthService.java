package com.example.diarybackend.services.auth;

import com.example.diarybackend.controllers.auth.requests.AuthRequest;
import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.controllers.auth.responses.TokenResponse;
import com.example.diarybackend.models.Identity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService extends UserDetailsService {

    TokenResponse register(IdentityRegisterRequest identityCreateRequest);

    TokenResponse authenticate(AuthRequest authRequest) throws Exception;

    TokenResponse refreshToken(String token);

}
