package com.mike.todolist.activity.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.mike.todolist.App;
import com.mike.todolist.R;
import com.mike.todolist.context.ApplicationContext;
import com.mike.todolist.context.DefaultContext;
import com.mike.todolist.context.DefaultSecurityContext;
import com.mike.todolist.context.SecurityContext;
import com.mike.todolist.context.UserRepresentation;
import com.mike.todolist.mapper.TokenMapper;
import com.mike.todolist.model.dto.AccessToken;
import com.mike.todolist.service.api.TokenApiClientService;
import com.mike.todolist.service.api.TokenApiClientServiceImpl;
import com.mike.todolist.service.api.UserApiClientService;
import com.mike.todolist.service.api.UserApiClientServiceImpl;

import java.util.List;
import java.util.Optional;

public class LoginActivity extends AppCompatActivity {
    private TokenApiClientService tokenApiClientService;
    private UserApiClientService userApiClientService;

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registerButton;

    public LoginActivity() {
        tokenApiClientService = new TokenApiClientServiceImpl();
        userApiClientService = new UserApiClientServiceImpl();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intent);
        });


        loginButton.setOnClickListener(view -> {
            Optional<AccessToken> accessTokenOptional = getAccessPasswordToken();

            if (accessTokenOptional.isPresent()) {
                setUpCurrentUser();
            } else {
                return;
            }

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Optional<AccessToken> getAccessPasswordToken() {
        return tokenApiClientService.getAccessPasswordToken(TokenMapper.toAccessTokenPRequest(
                getString(R.string.keycloak_verification_client_id),
                getString(R.string.keycloak_verification_grant_type),
                getString(R.string.keycloak_verification_scope),
                username.getText().toString(),
                password.getText().toString()
        ));
    }

    public void setUpCurrentUser() {
        Optional<List<UserRepresentation>> userRepOptional = userApiClientService
                .findUserByName(username.getText().toString());

        userRepOptional.ifPresent(userRepresentations -> userRepresentations
                .stream()
                .findFirst()
                .ifPresent(u -> {
                    ApplicationContext context = App.getInstance().getLocalApplicationContext();

                    if (context instanceof DefaultContext) {
                        DefaultContext defaultContext = (DefaultContext) context;
                        SecurityContext securityContext = defaultContext.getSecurityContext();

                        if (securityContext instanceof DefaultSecurityContext) {
                            DefaultSecurityContext defaultSecurityContext = (DefaultSecurityContext) securityContext;
                            defaultSecurityContext.setUserRepresentation(u);
                        }
                    }
                }));
    }
}
