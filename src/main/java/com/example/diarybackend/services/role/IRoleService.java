package com.example.diarybackend.services.role;

import com.example.diarybackend.models.Role;

public interface IRoleService {

    Role findRoleByName(String name);

}
