package com.mike.todolist.service.api;

import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;

import com.mike.todolist.client.RetrofitClientInstance;
import com.mike.todolist.client.TokenApiClient;
import com.mike.todolist.model.dto.AccessToken;
import com.mike.todolist.model.dto.AccessTokenCCRequest;
import com.mike.todolist.model.dto.AccessTokenPRequest;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;

public class TokenApiClientServiceImpl implements TokenApiClientService {
    public static Logger log = Logger.getLogger(TokenApiClientServiceImpl.class.getName());
    public static String BASE_URL = "http://10.0.2.2:9990";

    TokenApiClient tokenApiClient;

    public TokenApiClientServiceImpl() {
        tokenApiClient = RetrofitClientInstance.getInstance(BASE_URL).create(TokenApiClient.class);
    }
    
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Optional<AccessToken> getAccessPasswordToken(AccessTokenPRequest request) {
        Call<AccessToken> accessTokenCall = tokenApiClient
                .getAccessPToken(
                        request.getClientId(),
                        request.getGrantType(),
                        request.getScope(),
                        request.getUsername(),
                        request.getPassword()
                );

        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Response<AccessToken> tokenResponse = accessTokenCall.execute();

                if (Objects.nonNull(tokenResponse) && tokenResponse.isSuccessful()) {
                    return Optional.ofNullable(tokenResponse.body());
                } else {
                    log.warning("Unable to get access token by params:" + request + ". Response:" + tokenResponse);
                    return Optional.empty();
                }
            }
        } catch (IOException e) {
            log.severe("Unable to get access token by params:" + request + ".\n" + e);
        }

        return Optional.empty();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<AccessToken> getAccessClientCredentialsToken(AccessTokenCCRequest request) {
        Call<AccessToken> accessTokenCall = tokenApiClient
                .getAccessCCToken(
                        request.getClientId(),
                        request.getGrantType(),
                        request.getClientSecret(),
                        request.getScope()
                );

        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Response<AccessToken> tokenResponse = accessTokenCall.execute();

                if (Objects.nonNull(tokenResponse) && tokenResponse.isSuccessful()) {
                    return Optional.ofNullable(tokenResponse.body());
                } else {
                    log.warning("Unable to get access token by params:" + request + ". Response:" + tokenResponse);
                    return Optional.empty();
                }
            }
        } catch (IOException e) {
            log.severe("Unable to get access token by params:" + request + ".\n" + e);
        }

        return Optional.empty();
    }
}
