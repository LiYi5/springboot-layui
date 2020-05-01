package com.xiyouedu.bean;

public class RolePermissionVo {
    private Integer roleId;

    private String rolename;

    private Integer powerId;

    private String powername;

    @Override
    public String toString() {
        return "RolePermissionVo{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                ", powerId=" + powerId +
                ", powername='" + powername + '\'' +
                '}';
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public void setPowername(String powername) {
        this.powername = powername;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public String getPowername() {
        return powername;
    }
}
