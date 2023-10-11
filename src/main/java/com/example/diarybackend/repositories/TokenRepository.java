package com.example.diarybackend.repositories;

import com.example.diarybackend.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    Optional<Token> findTokenByUsername(String username);

    boolean existsTokenByValue(String token);

    void deleteTokenByValue(String token);

}
