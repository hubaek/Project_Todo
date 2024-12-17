package com.nbcamp.todoList.domain.user.controller.dto;

import com.nbcamp.todoList.domain.user.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private Long id;
    private String email;
    private UserRole role;

    public LoginResponse(String token, Long id, String email, UserRole role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
