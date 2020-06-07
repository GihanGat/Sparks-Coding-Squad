package com.cambrian.bookshare.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    // private boolean error;
    @SerializedName("token")
    private String token;
    // private User user;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}