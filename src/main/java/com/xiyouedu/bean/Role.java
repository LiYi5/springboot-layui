package com.xiyouedu.bean;

import java.util.List;

public class Role {
    private Integer roleId;

    private String rolename;

    private List<Power> power;//一个角色拥有多个权限

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                ", power=" + power +
                '}';
    }

    public List<Power> getPower() {
        return power;
    }

    public void setPowerList(List<Power> powerList) {
        this.power = powerList;
    }

    public List<Power> getPowerList() {
        return power;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }
}