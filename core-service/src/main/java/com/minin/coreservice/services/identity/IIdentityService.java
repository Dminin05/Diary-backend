package com.minin.coreservice.services.identity;

import com.minin.coreservice.models.Identity;
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
