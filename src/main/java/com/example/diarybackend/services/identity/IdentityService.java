package com.example.diarybackend.services.identity;

import com.example.diarybackend.models.Identity;
import com.example.diarybackend.repositories.IdentityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Optional<Identity> findById(UUID id) {
        return identityRepository.findById(id);
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
