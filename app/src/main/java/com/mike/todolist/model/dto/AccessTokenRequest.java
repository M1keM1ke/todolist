package com.mike.todolist.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenRequest {
    private String clientId;
    private String grantType;
    private String scope;
}
