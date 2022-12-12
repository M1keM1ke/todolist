package com.mike.todolist.mapper;

import com.mike.todolist.model.dto.UserRequestDto;

public class UserMapper {

    public static UserRequestDto toUserRequestDto(
            String username,
            String mail,
            String firstname,
            String lastname,
            String password
    ) {
        return new UserRequestDto(username, mail, firstname, lastname, password);
    }
}
