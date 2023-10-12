package com.example.diarybackend.services.identity;

import com.example.diarybackend.models.Identity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IIdentityService {


    List<Identity> findAll();

    Identity findById(UUID id);

    Identity save(Identity identity);

    Identity update(Identity identity);

    void deleteById(UUID id);

}
