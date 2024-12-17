package com.nbcamp.todoList.common.dto;

import com.nbcamp.todoList.domain.user.entity.UserRole;
import lombok.Getter;

@Getter
public class AuthMember {

    private final Long id;
    private final String email;
    private final UserRole userRole;
    private final String name;

    public AuthMember(Long id, String email, UserRole userRole, String name) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
        this.name = name;
    }
}