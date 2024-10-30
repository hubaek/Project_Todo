package com.nbcamp.todoList.domain.user.controller.dto;

import com.nbcamp.todoList.domain.user.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponse {
    private String name;
    private String email;

    public SignupResponse(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
