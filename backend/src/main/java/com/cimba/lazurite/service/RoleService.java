package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();
    Role findByName(String roleName);
}
