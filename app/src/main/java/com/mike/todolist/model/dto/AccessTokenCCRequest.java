package com.mike.todolist.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenCCRequest extends AccessTokenRequest {
    private String clientSecret;
}
