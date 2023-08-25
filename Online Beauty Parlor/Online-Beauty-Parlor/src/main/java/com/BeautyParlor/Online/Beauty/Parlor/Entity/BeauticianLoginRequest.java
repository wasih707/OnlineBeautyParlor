package com.BeautyParlor.Online.Beauty.Parlor.Entity;

public class BeauticianLoginRequest {
    private String beauticianId;
    private String password;

    public String getBeauticianId() {
        return beauticianId;
    }

    public void setBeauticianId(String beauticianId) {
        this.beauticianId = beauticianId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
