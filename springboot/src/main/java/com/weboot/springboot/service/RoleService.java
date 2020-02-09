package com.weboot.springboot.service;

import com.weboot.springboot.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolelist(Role role);
    String insertRole(Role role);
    String editRole(Role role);
    String deleteRole(String roleId);
    Role listRoleByRoleId(String roleId);
}
