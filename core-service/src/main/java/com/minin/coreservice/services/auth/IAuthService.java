package com.minin.coreservice.services.auth;

import com.minin.coreservice.controllers.auth.requests.authenticate.AuthRequest;
import com.minin.coreservice.controllers.auth.requests.passwords.ChangePasswordRequest;
import com.minin.coreservice.controllers.auth.requests.passwords.RecoveryPasswordRequest;
import com.minin.coreservice.controllers.auth.requests.registration.IdentityRegisterRequest;
import com.minin.coreservice.controllers.auth.responses.TokenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface IAuthService extends UserDetailsService {

    TokenResponse register(IdentityRegisterRequest identityCreateRequest);

    TokenResponse authenticate(AuthRequest authRequest) throws Exception;

    TokenResponse refreshToken(String token);

    TokenResponse changePassword(UUID identityId, ChangePasswordRequest changePasswordRequest);

    void passwordRecovery(UUID credentialsId, RecoveryPasswordRequest recoveryPasswordRequest);

}
