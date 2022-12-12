package com.mike.todolist.model.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class AccessToken {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private Integer expiresIn;

    public AccessToken(String accessToken,Integer expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
