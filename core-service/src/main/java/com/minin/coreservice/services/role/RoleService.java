package com.minin.coreservice.services.role;

import com.minin.exceptions.ResourceNotFoundException;
import com.minin.coreservice.models.Role;
import com.minin.coreservice.repositories.RoleRepository;
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
