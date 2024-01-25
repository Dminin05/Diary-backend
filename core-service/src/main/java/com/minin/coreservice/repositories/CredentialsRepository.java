package com.minin.coreservice.repositories;

import com.minin.coreservice.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, UUID> {

    Optional<Credentials> findCredentialsByUsername(String username);

    Optional<Credentials> findCredentialsByIdentity_Id(UUID identityId);

    Optional<Credentials> findCredentialsByEmail(String email);

    boolean existsCredentialsByUsername(String username);

    boolean existsCredentialsByEmail(String email);

}
