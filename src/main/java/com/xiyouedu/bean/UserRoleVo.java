package com.xiyouedu.bean;

public class UserRoleVo {
    private Integer userId;

    private String username;

    private String password;

    private String Rolename;

    private Integer roleId;

    @Override
    public String toString() {
        return "UserRoleVo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", Rolename='" + Rolename + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRolename(String Rolename) {
        this.Rolename = Rolename;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRolename() {
        return Rolename;
    }
}
