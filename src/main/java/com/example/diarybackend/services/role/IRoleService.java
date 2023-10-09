package com.example.diarybackend.services.role;

import com.example.diarybackend.models.Role;

import java.util.Optional;

public interface IRoleService {

    Optional<Role> findRoleByName(String name);

}
