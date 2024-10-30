package com.nbcamp.todoList.domain.user.controller.dto;

import com.nbcamp.todoList.domain.user.entity.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private Long id;
    private String email;
    private UserRoleEnum role;

    public LoginResponse(String token, Long id, String email, UserRoleEnum role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
