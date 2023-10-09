package com.example.diarybackend.services.credentials;

import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Identity;
import com.example.diarybackend.models.Role;
import com.example.diarybackend.repositories.CredentialsRepository;
import com.example.diarybackend.services.role.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CredentialsService implements ICredentialsService {

    private final CredentialsRepository credentialsRepository;
    private final IRoleService roleService;

    @Override
    public void create(String username, String password, String email, Identity identity) {

        Credentials credentials = new Credentials();

        List<Role> roles = new ArrayList<>();

        switch (identity.getType()) {
            case ADMIN -> {
                Role role = roleService.findRoleByName("ROLE_ADMIN")
                        .orElseThrow(); // TODO
                roles.add(role);
            }
            case STUDENT -> {
                Role role = roleService.findRoleByName("ROLE_STUDENT")
                        .orElseThrow(); // TODO
                roles.add(role);
            }
            case TEACHER -> {
                Role role = roleService.findRoleByName("ROLE_TEACHER")
                        .orElseThrow(); // TODO
                roles.add(role);
            }
        }

        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setEmail(email);
        credentials.setIdentity(identity);
        credentials.setEmailVerified(false);
        credentials.setRoles(roles);

        credentialsRepository.save(credentials);
    }

    @Override
    public Optional<Credentials> findByUsername(String username) {
        return credentialsRepository.findCredentialsByUsername(username);
    }

}
