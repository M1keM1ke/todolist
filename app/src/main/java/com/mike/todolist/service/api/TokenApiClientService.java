package com.mike.todolist.service.api;

import com.mike.todolist.model.dto.AccessToken;
import com.mike.todolist.model.dto.AccessTokenCCRequest;
import com.mike.todolist.model.dto.AccessTokenPRequest;

import java.util.Optional;

public interface TokenApiClientService {

    Optional<AccessToken> getAccessPasswordToken(AccessTokenPRequest request);

    Optional<AccessToken> getAccessClientCredentialsToken(AccessTokenCCRequest request);

}
