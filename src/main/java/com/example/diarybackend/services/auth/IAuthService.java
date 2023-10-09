package com.example.diarybackend.services.auth;

import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.models.Identity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService extends UserDetailsService {

    Identity register(IdentityRegisterRequest identityCreateRequest);

}
