package com.example.diarybackend.services.credentials;

import com.example.diarybackend.exceptions.ResourceNotFoundException;
import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Identity;
import com.example.diarybackend.models.Role;
import com.example.diarybackend.repositories.CredentialsRepository;
import com.example.diarybackend.services.role.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CredentialsService implements ICredentialsService {

    private final CredentialsRepository credentialsRepository;
    private final IRoleService roleService;

    @Override
    public Credentials create(String username, String password, String email, Identity identity) {

        Credentials credentials = new Credentials();
        List<Role> roles = new ArrayList<>();

        switch (identity.getType()) {
            case ADMIN -> {
                Role role = roleService.findRoleByName("ROLE_ADMIN");
                roles.add(role);
            }
            case STUDENT -> {
                Role role = roleService.findRoleByName("ROLE_STUDENT");
                roles.add(role);
            }
            case TEACHER -> {
                Role role = roleService.findRoleByName("ROLE_TEACHER");
                roles.add(role);
            }
        }

        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setEmail(email);
        credentials.setIdentity(identity);
        credentials.setEmailVerified(false);
        credentials.setRoles(roles);

        return credentialsRepository.save(credentials);
    }

    @Override
    public Credentials findByUsername(String username) {
        return credentialsRepository.findCredentialsByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("credentials_with_username_'%s'_not_found", username)));
    }

    @Override
    public Credentials findByIdentityId(UUID identityId) {
        return credentialsRepository.findCredentialsByIdentity_Id(identityId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("credentials_with_identity_id_'%s'_not_found", identityId)));
    }

    @Override
    public boolean isCredentialsExistsByUsername(String username) {
        return credentialsRepository.existsCredentialsByUsername(username);
    }

    @Override
    public boolean isCredentialsExistsByEmail(String email) {
        return credentialsRepository.existsCredentialsByEmail(email);
    }

}
