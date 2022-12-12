package com.mike.todolist.service.api;

import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;

import com.mike.todolist.App;
import com.mike.todolist.R;
import com.mike.todolist.client.RetrofitClientInstance;
import com.mike.todolist.client.UserApiClient;
import com.mike.todolist.context.UserRepresentation;
import com.mike.todolist.mapper.TokenMapper;
import com.mike.todolist.model.dto.AccessToken;
import com.mike.todolist.model.dto.AccessTokenCCRequest;
import com.mike.todolist.model.dto.UserRequestDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;

public class UserApiClientServiceImpl implements UserApiClientService {
    public static Logger log = Logger.getLogger(UserApiClientServiceImpl.class.getName());
    public static String BASE_URL = "http://10.0.2.2:8080";

    private UserApiClient userApiClient;
    private TokenApiClientService tokenApiClientService;

    public UserApiClientServiceImpl() {
        userApiClient = RetrofitClientInstance.getInstance(BASE_URL).create(UserApiClient.class);
        tokenApiClientService = new TokenApiClientServiceImpl();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void createUser(UserRequestDto request) {
        Optional<AccessToken> accessCCTokenOptional = getAccessCCToken();

        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                if (accessCCTokenOptional.isPresent()) {
                    AccessToken tokenResponse = accessCCTokenOptional.get();
                    Call<Void> user = userApiClient.createUser("Bearer " + tokenResponse.getAccessToken(), request);
                    Response<Void> execute = user.execute();
                }
            }
        } catch (IOException e) {
            log.severe("Unable to get access token by params:" + request + ".\n" + e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Optional<List<UserRepresentation>> findUserByName(String username) {
        Optional<AccessToken> accessCCTokenOptional = getAccessCCToken();

        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                if (accessCCTokenOptional.isPresent()) {
                    AccessToken tokenResponse = accessCCTokenOptional.get();

                    Response<List<UserRepresentation>> response = userApiClient
                            .findUserByName("Bearer " + tokenResponse.getAccessToken(), username).execute();
                    return Optional.ofNullable(response.body());
                }
            }
        } catch (IOException e) {
            log.severe("Unable to find user by username:" + username + ". " + e);
        }

        return Optional.empty();
    }

    public Optional<AccessToken> getAccessCCToken() {
        AccessTokenCCRequest accessTokenCCRequest = TokenMapper.toAccessTokenCCRequest(
                App.getInstance().getApplicationContext().getString(R.string.keycloak_todolist_client_id),
                App.getInstance().getApplicationContext().getString(R.string.keycloak_todolist_grant_type),
                App.getInstance().getApplicationContext().getString(R.string.keycloak_todolist_client_secret),
                App.getInstance().getApplicationContext().getString(R.string.keycloak_todolist_scope)
        );
        return tokenApiClientService.getAccessClientCredentialsToken(accessTokenCCRequest);
    }
}
