package com.xiyouedu.bean;

import java.util.List;

public class RoleP {

    private Integer roleId;

    private String rolename;

    private List<RolePower> rolePowers;

    @Override
    public String toString() {
        return "RoleP{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                ", rolePowers=" + rolePowers +
                '}';
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setRolePowers(List<RolePower> rolePowers) {
        this.rolePowers = rolePowers;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public List<RolePower> getRolePowers() {
        return rolePowers;
    }
}
