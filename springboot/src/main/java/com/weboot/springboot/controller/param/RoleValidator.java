package com.weboot.springboot.controller.param;

import com.weboot.springboot.domain.Role;
import com.weboot.springboot.utils.BeanCopierUtils;

import java.util.List;

public class RoleValidator {
    private String roleId;

    private String roleName;

    private String type;

    private String description;

    private List<String> permIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPermIds() {
        return permIds;
    }

    public void setPermIds(List<String> permIds) {
        this.permIds = permIds;
    }

    public Role genRole(){
        Role role = new Role();
        BeanCopierUtils.copyProperties(this,role);
        return role;
    }
}
