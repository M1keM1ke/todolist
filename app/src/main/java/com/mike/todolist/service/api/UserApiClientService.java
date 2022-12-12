package com.mike.todolist.service.api;

import com.mike.todolist.context.UserRepresentation;
import com.mike.todolist.model.dto.UserRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserApiClientService {
    Optional<List<UserRepresentation>> findUserByName(String username);

    void createUser(UserRequestDto request);
}