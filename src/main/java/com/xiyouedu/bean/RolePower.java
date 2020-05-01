package com.xiyouedu.bean;

public class RolePower {
    private Integer idRole;

    private Integer idPower;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdPower() {
        return idPower;
    }

    public void setIdPower(Integer idPower) {
        this.idPower = idPower;
    }

    @Override
    public String toString() {
        return "RolePower{" +
                "idRole=" + idRole +
                ", idPower=" + idPower +
                '}';
    }
}