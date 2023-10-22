package com.minin.diarybackend.services.auth;

import com.minin.diarybackend.controllers.auth.requests.AuthRequest;
import com.minin.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.minin.diarybackend.controllers.auth.responses.TokenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService extends UserDetailsService {

    TokenResponse register(IdentityRegisterRequest identityCreateRequest);

    TokenResponse authenticate(AuthRequest authRequest) throws Exception;

    TokenResponse refreshToken(String token);

}
