package com.minin.diarybackend.services.role;

import com.minin.diarybackend.models.Role;

public interface IRoleService {

    Role findRoleByName(String name);

}
