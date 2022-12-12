package com.mike.todolist.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mike.todolist.R;
import com.mike.todolist.mapper.UserMapper;
import com.mike.todolist.model.dto.UserRequestDto;
import com.mike.todolist.service.api.UserApiClientService;
import com.mike.todolist.service.api.UserApiClientServiceImpl;
import com.mike.todolist.utils.ToastUtils;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private EditText registerEditTextUsername;
    private EditText registerEditTextFirstname;
    private EditText registerEditTextLastname;
    private EditText registerEditTextEmailAddress;
    private EditText registerEditTextPassword;
    private EditText registerEditTextConfirmPassword;
    private Button registerButtonRegisterActivity;

    private UserApiClientService userApiClientService;

    public RegisterActivity() {
        userApiClientService = new UserApiClientServiceImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        registerEditTextUsername = findViewById(R.id.registerEditTextUsername);
        registerEditTextFirstname = findViewById(R.id.registerEditTextFirstname);
        registerEditTextLastname = findViewById(R.id.registerEditTextLastname);
        registerEditTextEmailAddress = findViewById(R.id.registerEditTextEmailAddress);
        registerEditTextPassword = findViewById(R.id.registerEditTextPassword);
        registerEditTextConfirmPassword = findViewById(R.id.registerEditTextConfirmPassword);

        registerButtonRegisterActivity = findViewById(R.id.registerButtonRegisterActivity);

        registerButtonRegisterActivity.setOnClickListener(view -> {
            if (!isValidFields()) {
                return;
            }

            userApiClientService.createUser(buildUserRequestDto());

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            RegisterActivity.this.startActivity(intent);
        });
    }

    public boolean isValidFields() {
        Editable registerEditTextUsernameText = registerEditTextUsername.getText();
        if (Objects.isNull(registerEditTextUsernameText) || registerEditTextUsernameText.toString().isEmpty()) {
            ToastUtils.makeShortToast(getApplicationContext(), "Username must not be empty");
            return false;
        }

        Editable registerEditTextFirstnameText = registerEditTextFirstname.getText();
        if (Objects.isNull(registerEditTextFirstnameText) || registerEditTextFirstnameText.toString().isEmpty()) {
            ToastUtils.makeShortToast(getApplicationContext(), "Firstname must not be empty");
            return false;
        }

        Editable registerEditTextLastnameText = registerEditTextLastname.getText();
        if (Objects.isNull(registerEditTextLastnameText) || registerEditTextLastnameText.toString().isEmpty()) {
            ToastUtils.makeShortToast(getApplicationContext(), "Lastname must not be empty");
            return false;
        }

        Editable registerEditTextEmailAddressText = registerEditTextEmailAddress.getText();
        if (Objects.isNull(registerEditTextEmailAddressText) || registerEditTextEmailAddressText.toString().isEmpty()) {
            ToastUtils.makeShortToast(getApplicationContext(), "Mail must not be empty");
            return false;
        }

        Editable registerEditTextPasswordText = registerEditTextPassword.getText();
        if (Objects.isNull(registerEditTextPasswordText) || registerEditTextPasswordText.toString().isEmpty()) {
            ToastUtils.makeShortToast(getApplicationContext(), "Password must not be empty");
            return false;
        }

        Editable registerEditTextConfirmPassword = this.registerEditTextConfirmPassword.getText();
        if (Objects.isNull(registerEditTextConfirmPassword) || registerEditTextConfirmPassword.toString().isEmpty()) {
            ToastUtils.makeShortToast(getApplicationContext(), "Confirm password must not be empty");
            return false;
        }

        if (!registerEditTextPassword.getText().toString().equals(registerEditTextConfirmPassword.toString())) {
            ToastUtils.makeShortToast(getApplicationContext(), "Passwords are not equals");
            return false;
        }

        return true;
    }

    private UserRequestDto buildUserRequestDto() {
        return UserMapper.toUserRequestDto(
                registerEditTextUsername.getText().toString(),
                registerEditTextEmailAddress.getText().toString(),
                registerEditTextFirstname.getText().toString(),
                registerEditTextLastname.getText().toString(),
                registerEditTextPassword.getText().toString()
        );
    }
}
