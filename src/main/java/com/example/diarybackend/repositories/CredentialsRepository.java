package com.example.diarybackend.repositories;

import com.example.diarybackend.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, UUID> {

    Optional<Credentials> findCredentialsByUsername(String username);

    Optional<Credentials> findCredentialsByIdentity_Id(UUID identityId);

    boolean existsCredentialsByUsername(String username);

    boolean existsCredentialsByEmail(String email);

}
