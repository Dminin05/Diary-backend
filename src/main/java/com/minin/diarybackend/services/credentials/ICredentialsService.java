package com.minin.diarybackend.services.credentials;

import com.minin.diarybackend.models.Credentials;
import com.minin.diarybackend.models.Identity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICredentialsService {

    Credentials create(String username, String password, String email, Identity identity);

    Credentials findById(UUID id);

    Credentials findByUsername(String username);

    Credentials findByEmail(String email);

    Credentials findByIdentityId(UUID identityId);

    void update(Credentials credentials);

    void updateEmail(UUID identityId, String email);

    void verifyEmail(UUID identityId, String code);

    boolean isCredentialsExistsByUsername(String username);

    boolean isCredentialsExistsByEmail(String email);

}
