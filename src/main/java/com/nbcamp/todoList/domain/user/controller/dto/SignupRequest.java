package com.nbcamp.todoList.domain.user.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
