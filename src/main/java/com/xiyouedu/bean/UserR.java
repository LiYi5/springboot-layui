package com.xiyouedu.bean;

import java.util.List;

public class UserR {
    private Integer userId;

    private String username;

    private String password;

    private List<UserRole> userRoles;

    @Override
    public String toString() {
        return "UserR{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
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

    public List<UserRole> getUserRoles() {
        return userRoles;
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

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
