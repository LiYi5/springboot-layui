package com.xiyouedu.bean;

public class Power {
    private Integer powerId;

    private String powername;

    @Override
    public String toString() {
        return "Power{" +
                "powerId=" + powerId +
                ", powername='" + powername + '\'' +
                ", powerurl='" + powerurl + '\'' +
                '}';
    }

    private String powerurl;

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getPowername() {
        return powername;
    }

    public void setPowername(String powername) {
        this.powername = powername == null ? null : powername.trim();
    }

    public String getPowerurl() {
        return powerurl;
    }

    public void setPowerurl(String powerurl) {
        this.powerurl = powerurl == null ? null : powerurl.trim();
    }
}