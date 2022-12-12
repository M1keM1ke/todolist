package com.mike.todolist.client;

import com.mike.todolist.model.dto.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TokenApiClient {
    @FormUrlEncoded
    @POST("/auth/realms/MOBILE_PROJECT/protocol/openid-connect/token")
    Call<AccessToken> getAccessPToken(
            @Field("client_id") String clientId,
            @Field("grant_type") String grantType,
            @Field("scope") String scope,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/auth/realms/MOBILE_PROJECT/protocol/openid-connect/token")
    Call<AccessToken> getAccessCCToken(
            @Field("client_id") String clientId,
            @Field("grant_type") String grantType,
            @Field("client_secret") String clientSecret,
            @Field("scope") String scope
    );
}
