package com.example.diarybackend.services.credentials;

import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Identity;
import org.springframework.stereotype.Repository;

@Repository
public interface ICredentialsService {

    Credentials create(String username, String password, String email, Identity identity);

    Credentials findByUsername(String username);

    boolean isCredentialsExistsByUsername(String username);

    boolean isCredentialsExistsByEmail(String email);

}
