package com.minin.diarybackend.services.credentials;

import com.minin.diarybackend.exceptions.BadRequestException;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.models.Credentials;
import com.minin.diarybackend.models.Identity;
import com.minin.diarybackend.models.Role;
import com.minin.diarybackend.models.VerificationCode;
import com.minin.diarybackend.repositories.CredentialsRepository;
import com.minin.diarybackend.services.codes.IVerificationCodeService;
import com.minin.diarybackend.services.role.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CredentialsService implements ICredentialsService {

    private final CredentialsRepository credentialsRepository;
    private final IRoleService roleService;
    private final IVerificationCodeService verificationCodeService;

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
    public void update(Credentials credentials) {
        credentialsRepository.save(credentials);
    }

    @Override
    public void updateEmail(UUID identityId, String email) {

        if (isCredentialsExistsByEmail(email)) {
            throw new BadRequestException("this_email_already_exists");
        }

        Credentials credentials = findByIdentityId(identityId);

        credentials.setEmail(email);
        credentials.setEmailVerified(false);

        update(credentials);
    }

    @Override
    public void verifyEmail(UUID identityId, String code) {

        Credentials credentials = findByIdentityId(identityId);
        VerificationCode verificationCode = verificationCodeService.findByValue(code);

        boolean isVerificationCodeValid = verificationCode.getExpiresAt().isAfter(LocalDateTime.now());

        if (verificationCode.getValue().equals(code) && isVerificationCodeValid) {
            credentials.setEmailVerified(true);
            verificationCodeService.deleteById(verificationCode.getId());
        }
    }

    @Override
    public Credentials findById(UUID id) {
        return credentialsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("credentials_not_found"));
    }

    @Override
    public Credentials findByUsername(String username) {
        return credentialsRepository.findCredentialsByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("credentials_not_found"));
    }

    @Override
    public Credentials findByEmail(String email) {
        return credentialsRepository.findCredentialsByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("credentials_not_found"));
    }

    @Override
    public Credentials findByIdentityId(UUID identityId) {
        return credentialsRepository.findCredentialsByIdentity_Id(identityId)
                .orElseThrow(() -> new ResourceNotFoundException("credentials_not_found"));
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
