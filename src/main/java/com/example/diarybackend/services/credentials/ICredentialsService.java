package com.example.diarybackend.services.credentials;

import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Identity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICredentialsService {

    void create(String username, String password, String email, Identity identity);

    Optional<Credentials> findByUsername(String username);

}
