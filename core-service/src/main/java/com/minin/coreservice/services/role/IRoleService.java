package com.minin.coreservice.services.role;

import com.minin.coreservice.models.Role;

public interface IRoleService {

    Role findRoleByName(String name);

}
