package com.example.diarybackend.repositories;

import com.example.diarybackend.models.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IdentityRepository extends JpaRepository<Identity, UUID> {
}
