package com.mike.todolist.mapper;

import com.mike.todolist.model.dto.AccessTokenCCRequest;
import com.mike.todolist.model.dto.AccessTokenPRequest;

public class TokenMapper {

    public static AccessTokenCCRequest toAccessTokenCCRequest(
            String clientId,
            String grantType,
            String clientSecret,
            String scope
    ) {
        AccessTokenCCRequest accessTokenCCRequest = new AccessTokenCCRequest();

        accessTokenCCRequest.setClientId(clientId);
        accessTokenCCRequest.setGrantType(grantType);
        accessTokenCCRequest.setClientSecret(clientSecret);
        accessTokenCCRequest.setScope(scope);

        return accessTokenCCRequest;
    }

    public static AccessTokenPRequest toAccessTokenPRequest(
            String clientId,
            String grantType,
            String scope,
            String username,
            String password
    ) {
        AccessTokenPRequest accessTokenPRequest = new AccessTokenPRequest();

        accessTokenPRequest.setClientId(clientId);
        accessTokenPRequest.setGrantType(grantType);
        accessTokenPRequest.setScope(scope);
        accessTokenPRequest.setUsername(username);
        accessTokenPRequest.setPassword(password);

        return accessTokenPRequest;
    }
}
