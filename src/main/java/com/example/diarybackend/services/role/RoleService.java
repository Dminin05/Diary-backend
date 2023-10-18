package com.example.diarybackend.services.role;

import com.example.diarybackend.exceptions.ResourceNotFoundException;
import com.example.diarybackend.models.Role;
import com.example.diarybackend.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("role_not_found"));
    }

}
