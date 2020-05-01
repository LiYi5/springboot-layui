package com.xiyouedu.bean;

public class UserRole {
    private Integer idUser;

    private Integer idRole;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "idUser=" + idUser +
                ", idRole=" + idRole +
                '}';
    }
}