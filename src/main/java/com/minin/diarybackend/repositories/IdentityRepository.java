package com.minin.diarybackend.repositories;

import com.minin.diarybackend.models.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IdentityRepository extends JpaRepository<Identity, UUID> {
}
