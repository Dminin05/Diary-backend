package com.minin.diarybackend.services.identity;

import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.models.Identity;
import com.minin.diarybackend.repositories.IdentityRepository;
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
