package com.mike.todolist.client;

import com.mike.todolist.context.UserRepresentation;
import com.mike.todolist.model.dto.UserRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApiClient {
    @GET("api/v1/user/name/{name}")
    Call<List<UserRepresentation>> findUserByName(@Header("Authorization") String authHeader, @Path(value = "name") String username);

    @POST("api/v1/user")
    Call<Void> createUser(@Header("Authorization") String authHeader, @Body UserRequestDto request);
}
