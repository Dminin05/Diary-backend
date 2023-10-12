package com.example.diarybackend.services.identity;

import com.example.diarybackend.exceptions.ResourceNotFoundException;
import com.example.diarybackend.models.Identity;
import com.example.diarybackend.repositories.IdentityRepository;
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
                .orElseThrow(() -> new ResourceNotFoundException(String.format("identity_with_id_'%s'_not_found", id)));
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
