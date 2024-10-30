package com.nbcamp.todoList.domain.todo.controller.dto;

import lombok.Getter;

@Getter
public class TodoCreateRequest {

    private String title;
    private String content;
}
