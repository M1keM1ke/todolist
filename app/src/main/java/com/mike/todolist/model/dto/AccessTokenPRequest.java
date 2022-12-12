package com.mike.todolist.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenPRequest extends AccessTokenRequest {
    private String username;
    private String password;


}

