package com.xiyouedu.bean;

import java.util.List;

public class UserRo {
    private Integer userId;

    private String username;

    private String password;

    private List<Integer> roleId;

    @Override
    public String toString() {
        return "UserRo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
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

    public List<Integer> getRoleId() {
        return roleId;
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

    public void setRoleId(List<Integer> roleId) {
        this.roleId = roleId;
    }
}
