package com.minin.diarybackend.services.credentials;

import com.minin.diarybackend.models.Credentials;
import com.minin.diarybackend.models.Identity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICredentialsService {

    Credentials create(String username, String password, String email, Identity identity);

    Credentials findByUsername(String username);

    Credentials findByIdentityId(UUID identityId);

    boolean isCredentialsExistsByUsername(String username);

    boolean isCredentialsExistsByEmail(String email);

}
