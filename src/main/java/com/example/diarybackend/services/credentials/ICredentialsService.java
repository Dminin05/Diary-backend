package com.example.diarybackend.services.credentials;

import com.example.diarybackend.models.Identity;
import org.springframework.stereotype.Repository;

@Repository
public interface ICredentialsService {

    void create(String username, String password, String email, Identity identity);

}
