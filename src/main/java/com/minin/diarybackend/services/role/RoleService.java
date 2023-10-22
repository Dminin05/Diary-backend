package com.minin.diarybackend.services.role;

import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.models.Role;
import com.minin.diarybackend.repositories.RoleRepository;
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
