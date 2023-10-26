package com.minin.diarybackend.services.auth;

import com.minin.diarybackend.controllers.auth.requests.authenticate.AuthRequest;
import com.minin.diarybackend.controllers.auth.requests.passwords.ChangePasswordRequest;
import com.minin.diarybackend.controllers.auth.requests.passwords.RecoveryPasswordRequest;
import com.minin.diarybackend.controllers.auth.requests.registration.IdentityRegisterRequest;
import com.minin.diarybackend.controllers.auth.responses.TokenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface IAuthService extends UserDetailsService {

    TokenResponse register(IdentityRegisterRequest identityCreateRequest);

    TokenResponse authenticate(AuthRequest authRequest) throws Exception;

    TokenResponse refreshToken(String token);

    TokenResponse changePassword(UUID identityId, ChangePasswordRequest changePasswordRequest);

    void passwordRecovery(UUID credentialsId, RecoveryPasswordRequest recoveryPasswordRequest);

}
