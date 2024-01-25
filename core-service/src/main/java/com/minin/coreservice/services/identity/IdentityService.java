package com.minin.coreservice.services.identity;

import com.minin.exceptions.ResourceNotFoundException;
import com.minin.coreservice.models.Identity;
import com.minin.coreservice.repositories.IdentityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdentityService implements IIdentityService {

    private final IdentityRepository identityRepository;

    @Override
    public List<Identity> findAll() {
        return identityRepository.findAll();
    }

    @Override
    public Identity findById(UUID id) {
        return identityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("identity_not_found"));
    }

    @Override
    public Identity save(Identity identity) {
        return identityRepository.save(identity);
    }

    @Override
    public Identity update(Identity identity) {
        return identityRepository.save(identity);
    }

    @Override
    public void deleteById(UUID id) {
        identityRepository.deleteById(id);
    }

}
