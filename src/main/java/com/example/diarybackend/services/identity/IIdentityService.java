package com.example.diarybackend.services.identity;

import com.example.diarybackend.models.Identity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IIdentityService {


    List<Identity> findAll();

    Optional<Identity> findById(UUID id);

    Identity save(Identity identity);

    Identity update(Identity identity);

    void deleteById(UUID id);

}
