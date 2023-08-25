package com.BeautyParlor.Online.Beauty.Parlor.Entity;

public class CustomerLoginRequest {
    private String userId;
    private String Password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
