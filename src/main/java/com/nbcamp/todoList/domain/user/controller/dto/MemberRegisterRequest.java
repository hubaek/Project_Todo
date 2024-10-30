package com.nbcamp.todoList.domain.user.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRegisterRequest {

    String name;
    String email;
}
