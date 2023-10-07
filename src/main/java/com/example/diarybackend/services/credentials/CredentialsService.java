package com.example.diarybackend.services.credentials;

import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Identity;
import com.example.diarybackend.repositories.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialsService implements ICredentialsService{

    private final CredentialsRepository credentialsRepository;

    @Override
    public void create(String username, String password, String email, Identity identity) {

        Credentials credentials = new Credentials();

        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setEmail(email);
        credentials.setIdentity(identity);
        credentials.setEmailVerified(false);

        credentialsRepository.save(credentials);
    }

}
