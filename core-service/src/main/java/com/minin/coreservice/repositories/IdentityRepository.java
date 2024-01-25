package com.minin.coreservice.repositories;

import com.minin.coreservice.models.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IdentityRepository extends JpaRepository<Identity, UUID> {
}
